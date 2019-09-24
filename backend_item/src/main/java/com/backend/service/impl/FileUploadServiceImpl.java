package com.backend.service.impl;

import com.backend.service.FileUploadService;
import com.common.utils.FtpUtil;
import com.common.utils.IDUtils;
import com.common.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH_HOME}")
    private String FTP_PATH_HOME;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public Result fileUpload(MultipartFile file) {
        try {
            DateFormat format = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = format.format(new Date());
            String fileName= IDUtils.genImageName()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH_HOME, path, fileName, file.getInputStream());
            String imageUrl="http://"+FTP_HOST+path+fileName;
            return Result.ok(imageUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("上传失败");
    }

}
