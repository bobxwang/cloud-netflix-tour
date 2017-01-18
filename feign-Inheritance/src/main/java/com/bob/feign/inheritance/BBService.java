package com.bob.feign.inheritance;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by bob on 16/8/20.
 */
public interface BBService {

    @RequestMapping(method = RequestMethod.GET, value = "/bbservice/{id}")
    Map<String, String> getMap(@PathVariable("id") long id);
}