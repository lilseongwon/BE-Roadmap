package com.example.s3example.domain.image.presentation;

import com.example.s3example.domain.image.service.UpLoadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/image")
@RestController
public class ImageController {

    private final UpLoadImageService upLoadImageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImageUrlResponse upload(@RequestPart List<MultipartFile> images) {
        return upLoadImageService.execute(images);
    }
}