package com.backend.service;

import com.common.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传service
 */
public interface FileUploadService {
    Result fileUpload(MultipartFile file);
}
