package com.bob.cloud.netflix.apigateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SpringConfig {

    final Logger slogger = LoggerFactory.getLogger(SpringConfig.class);

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

    @ControllerAdvice(annotations = RestController.class)
    public class RestErrorHandler extends ResponseEntityExceptionHandler {

        MessageSource messageSource;

        @Autowired
        public RestErrorHandler(MessageSource messageSource) {
            this.messageSource = messageSource;
        }

        /**
         * 观察/beans，可以看到spring自动会注入一个ObjectMapper实例，因为springmvc默认用其来进行josn的序列化
         */
        @Autowired
        private ObjectMapper jacksonObjectMapper;

        @ExceptionHandler({Exception.class})
        @ResponseBody
        ResponseEntity<VndErrors> handleBadRequests(HttpServletRequest request, HttpServletResponse response,
                                                    Exception ex) throws IOException {
            VndErrors vndErrors;
            String logref = ex.getClass().getSimpleName();

            try {
                logError(ex, request);

                if (ex instanceof NumberFormatException) {
                    response.setStatus(400);
                    vndErrors = new VndErrors(logref, ex.getMessage());
                } else {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    vndErrors = new VndErrors(logref, ex.getMessage());
                }
            } catch (Exception e) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                vndErrors = new VndErrors(logref, ex.getMessage());
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            /**
             * 通过改写mediatype，使客户端感知是哪种类型的feign errorcode，方便其进行反序列化
             */
            httpHeaders.setContentType(MediaType.parseMediaType("application/vnd.error+json"));
            return new ResponseEntity<>(vndErrors, httpHeaders, HttpStatus.valueOf(response.getStatus()));
        }

        /**
         * 重载ResponseEntityExceptionHandler的方法,加入日志
         */
        @Override
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                                 HttpStatus status, WebRequest request) {
            logError(ex);

            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
                request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
            }

            return new ResponseEntity<>(body, headers, status);
        }

        private void logError(Exception ex) {
            Map<String, String> map = Maps.newHashMap();
            map.put("message", ex.getMessage());

            try {
                slogger.error("{}", jacksonObjectMapper.writeValueAsString(map), ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void logError(Exception ex, HttpServletRequest request) {
            Map<String, String> map = Maps.newHashMap();
            map.put("message", ex.getMessage());
            map.put("from", request.getRemoteAddr());
            String queryString = request.getQueryString();
            map.put("path", queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI());

            try {
                slogger.error("{}", jacksonObjectMapper.writeValueAsString(map), ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}