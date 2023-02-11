package com.rika.wxspringbootbackend.controller;

import com.rika.wxspringbootbackend.entity.net.Resp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/userAuth")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Resp> userAuth() {
        return ResponseEntity.ok(new Resp(200, "这是一个用户权限的接口", null));
    }

    @RequestMapping("/adminAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Resp> adminAuth() {
        return ResponseEntity.ok(new Resp(200, "这是一个管理权限的接口", null));
    }

    @RequestMapping("/image")
    public ResponseEntity<byte[]> getImage() throws IOException {
        File file = new File("D:/pix/slide/key.jpg");
        byte[] content = Files.readAllBytes(file.toPath());

        // 设置 MIME 类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        // 设置内容长度
        headers.setContentLength(content.length);

        return ResponseEntity.ok().headers(headers).body(content);
    }

    @RequestMapping("/upload")
    public ResponseEntity<String> receiveImg(@RequestParam("image") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:/pix/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.ok().body("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to upload image");
        }
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        File file = new File("D:/pix/恋×シンアイ彼女/星奏.png");
        byte[] content = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(content.length);

        return ResponseEntity.ok().headers(headers).body(content);
    }

}
