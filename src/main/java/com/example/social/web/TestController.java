package com.example.social.web;

import com.example.social.dto.AuthRequestBody;
import com.example.social.dto.GoogleResponse;
import com.example.social.dto.TestData;
import com.example.social.entities.Friends;
import com.example.social.repo.sm_repos.FriendRepo;
import com.example.social.repo.sm_repos.PostRepository;
import com.example.social.services.auth.AuthService;
import com.example.social.utils.ImageProcessor;
import com.example.social.utils.JwtUtil;
import com.example.social.utils.VideoProcessor;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.util.List;



@CrossOrigin
@RestController
public class TestController {

    @Autowired
    private PostRepository repository;

    @Autowired
    private FriendRepo repo;

    @Autowired
    private VideoProcessor videoProcessor;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test(){
        return "Hello from docker";
    }

    @GetMapping(value = "/image",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getImage(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ImageProcessor.decompressImage(repository.findImage()));
    }


    @GetMapping("/friends/{email}")
    public List<Friends> getFriends(@PathVariable("email")String email){
        return repo.findByUserEmail(email);
    }


    @PostMapping("/video")
    public String video(@RequestParam("video")MultipartFile file) throws IOException {
        return videoProcessor.uploadVideo(file);
    }

    @GetMapping("/video")
    public ResponseEntity<Resource> getVideo() {
        File file = new File("C:/social/videos/7a3cf823-a816-408f-8501-ab2888208c07.mp4");
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(resource);
    }

    @GetMapping(value = "/test/video")
    public void test2(HttpServletResponse response) throws IOException {
        String filePath ="C:/social/videos/d7359f86-113d-4988-8e04-12d17ba94db4.mp4";
        InputStream inputStream = new FileInputStream(filePath);
        response.setContentType(String.valueOf(MediaType.parseMediaType("video/mp4")));
        StreamUtils.copy(inputStream,response.getOutputStream());
    }

    @GetMapping("/test2/video")
    public byte[] test3() throws IOException {
        byte[] video = Files.readAllBytes(new File("C:/social/videos/d7359f86-113d-4988-8e04-12d17ba94db4.mp4").toPath());
        return video;
    }

    @PostMapping("/token")
    public String test4( @RequestBody AuthRequestBody authRequestBody) {
        GoogleResponse googleResponse = authService.verifyTokenOfGoogleSSO(authRequestBody);
        return authService.generateJwtToken(authService.setUserDetails(googleResponse),googleResponse.getEmail());
    }

    @PostMapping("/token/test")
    private void test5(@RequestBody TestData testData){
        String username = authService.getUserEmailFromToken(testData.getToken());
        System.out.println(username);
    }

}
