package com.nongfu.kakax.basemain.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorController extends BasicErrorController {
    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS));
        HttpStatus status = getStatus(request);

        ApiErrorResult apiErrorResult = new ApiErrorResult(errorAttributes);
        return new ResponseEntity<>(apiErrorResult,status);
    }

    public class ApiErrorResult extends LinkedHashMap<String,Object> {
        private static final String SUCCESS_KEY = "success";
        private static final String CODE_KEY = "code";
        private static final String MESSAGE_KEY =  "message";

        public ApiErrorResult(Map<String, Object> errorAttributes) {
            String code = errorAttributes.get("status").toString();
            String message = errorAttributes.get("message").toString();
            String exception = errorAttributes.get("exception").toString();

            this.put(CODE_KEY,code);
            this.put(MESSAGE_KEY,message);
        }
    }
}
