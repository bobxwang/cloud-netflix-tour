package com.bob.cloud.netflix.apigateway.overrideobj;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    /**
     * Implement this method in order to decode an HTTP {@link feign.Response} when {@link
     * feign.Response#status()} is not in the 2xx range. Please raise  application-specific exceptions where
     * possible. If your exception is retryable, wrap or subclass {@link RetryableException}
     *
     * @param methodKey {@link feign.Feign#configKey} of the java method that invoked the request.
     *                  ex. {@code IAM#getUser()}
     * @param response  HTTP response where {@link feign.Response#status() status} is greater than or equal
     *                  to {@code 300}.
     * @return Exception IOException, if there was a network error reading the response or an
     * application-specific exception decoded by the implementation. If the throwable is retryable, it
     * should be wrapped, or a subtype of {@link RetryableException}
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 400 && response.status() <= 499) {
            // we can according the http status to do what we want
        }
        if (response.status() >= 500 && response.status() <= 599) {

        }
        return FeignException.errorStatus(methodKey, response);
    }
}