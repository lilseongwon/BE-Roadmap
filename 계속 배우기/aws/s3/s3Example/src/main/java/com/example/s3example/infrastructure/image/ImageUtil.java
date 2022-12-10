package com.example.s3example.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {

    String uploadImage(MultipartFile image);
}