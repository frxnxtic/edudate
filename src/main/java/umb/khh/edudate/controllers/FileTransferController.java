package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umb.khh.edudate.services.FtpUploadService;

@RestController
public class FileTransferController {

    @Autowired
    private FtpUploadService ftpUploadService;

    @PostMapping("/ftp-upload")
    public String uploadFile(@RequestParam Long userId, @RequestParam MultipartFile file) {
        return ftpUploadService.uploadFileToFtpServer(userId, file);
    }
}
