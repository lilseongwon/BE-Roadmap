package com.example.s3example.infrastructure.image.exception;

import com.example.s3example.global.error.CustomException;
import com.example.s3example.global.error.ErrorCode;

public class ImageValueNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new ImageValueNotFoundException();

    private ImageValueNotFoundException() {
        super(ErrorCode.IMAGE_VALUE_NOT_FOUND);
    }
}
