package com.example.s3example.domain.image.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageUrlResponse {

    private final List<String> imageUrl;
}
