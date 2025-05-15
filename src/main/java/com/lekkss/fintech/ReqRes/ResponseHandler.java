package com.lekkss.fintech.ReqRes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(
            String message,
            HttpStatus status,
            Object responseObj,
            Boolean error // can be null if no error
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", status.value());
        map.put("message", message);
        map.put("data", responseObj);
        map.put("error", error);

        return new ResponseEntity<>(map, status);
    }
}