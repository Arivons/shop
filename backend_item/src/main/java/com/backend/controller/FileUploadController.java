package com.backend.controller;

import com.backend.service.FileUploadService;
import com.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file){
        return fileUploadService.fileUpload(file);
    }

}
