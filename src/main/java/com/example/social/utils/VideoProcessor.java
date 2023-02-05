package com.example.social.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Component
public class VideoProcessor {



    @Value("${projects.videos}")
    private String path;
    @Value("${directory.path}")
    private String directoryPath;




    public String uploadVideo(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String randomUUID = UUID.randomUUID().toString();
        String fileName = randomUUID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath = directoryPath + File.separator + fileName;

        File f = new File(directoryPath);

        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
//        filePath = filePath.replace("\\","");
        return fileName;
    }

//    public static Resource downloadVideo(String videoPath) {
//        File file = new File(videoPath);
//        return new FileSystemResource(file);
//    }

// byte[] video = Files.readAllBytes(new File(videoPath).toPath());
//        return video;
}
