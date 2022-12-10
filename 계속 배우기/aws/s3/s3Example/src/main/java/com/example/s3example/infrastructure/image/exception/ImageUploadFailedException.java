package com.example.s3example.infrastructure.image.exception;

import com.example.s3example.global.error.CustomException;
import com.example.s3example.global.error.ErrorCode;

public class ImageUploadFailedException extends CustomException {

    public static final CustomException EXCEPTION =
            new ImageUploadFailedException();

    private ImageUploadFailedException() {
        super(ErrorCode.IMAGE_UPLOAD_FAILED);
    }

}