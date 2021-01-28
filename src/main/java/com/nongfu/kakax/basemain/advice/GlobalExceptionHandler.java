package com.nongfu.kakax.basemain.advice;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map signatureException() {
        Map<String, String> map = Maps.newHashMap();
        map.put("code", "00001");
        map.put("msg", "Unauthorized");
        return map;
    }
}
