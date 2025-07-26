package com.awesomeforum.aaf.dto.exception;


public record ApiException(String message,
                           String errorCode,
                           int httpStatus,
                           String timestamp) {
}
