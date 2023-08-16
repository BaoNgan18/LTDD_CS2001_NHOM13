package com.example.apiroy.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apiroy.Service.CoverImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class CoverImgServiceImpl implements CoverImgService {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
}
