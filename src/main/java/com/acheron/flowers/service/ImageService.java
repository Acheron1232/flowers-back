package com.acheron.flowers.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 s3;

    @SneakyThrows
    public String saveImage(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        String path = UUID.randomUUID() + String.valueOf(System.currentTimeMillis()) + file.getOriginalFilename();
        PutObjectRequest request = new PutObjectRequest("security-module-devx", path, file.getInputStream(), objectMetadata);
        PutObjectResult putObjectResult = s3.putObject(request);
        return s3.getUrl("security-module-devx", path).toString();
    }
}