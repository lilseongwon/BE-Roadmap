package com.example.s3example.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    IMAGE_UPLOAD_FAILED(400, "Image Upload Failed"),
    IMAGE_VALUE_NOT_FOUND(404, "Image Value Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");


    private final int status;
    private final String message;
}