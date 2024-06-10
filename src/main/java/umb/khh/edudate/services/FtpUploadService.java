package umb.khh.edudate.services;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.repositories.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FtpUploadService {

    @Autowired
    private UserRepository userRepository;

    public String uploadFileToFtpServer(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String server = "ftp.endora.cz";
        int port = 21;
        String userFtp = "khhjecoolnet";
        String passFtp = "vunhi8-fepkYr-fubjat";
        String remoteDir = "/khh.jecool.net/web/img/";


        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(userFtp, passFtp);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File tempFile = File.createTempFile("upload", file.getOriginalFilename());
            file.transferTo(tempFile);
            String remoteFilePath = remoteDir + "/" + user.getId() + ".jpg";

            try (FileInputStream inputStream = new FileInputStream(tempFile)) {
                boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
                if (done) {
                    return "The file is uploaded successfully.";
                } else {
                    return "Could not upload the file.";
                }
            } finally {
                tempFile.delete();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
