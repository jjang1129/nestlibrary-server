package com.server.nestlibrary.controller;

import com.server.nestlibrary.model.dto.UserDTO;
import com.server.nestlibrary.model.vo.User;
import com.server.nestlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(UserDTO dto) throws Exception {
        // 폴더생성 완료
        Path directoryPath = Paths.get("\\\\\\\\192.168.10.51\\\\upload\\\\nestlibrary\\\\user\\" + dto.getUserEmail() + "\\");
        Files.createDirectories(directoryPath);
        // dto vo로 포장
        System.out.println(dto);
        User vo = new User()
                .builder()
                .userEmail(dto.getUserEmail())
                .userPassword(dto.getUserPassword())
                .userNickname(dto.getUserNickname())
                .userImgUrl(fileUpload(dto.getUserImgUrl(), dto.getUserEmail()))
                .userInfo(dto.getUserInfo())
                .build();
        System.out.println(vo);
        userService.registerUser(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/nickname")
    public ResponseEntity nicknameCheck(String nickname){
        User user =  userService.findByNickname(nickname); // 있으면 중복 닉네임
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }




    public String fileUpload(MultipartFile file, String email) throws IllegalStateException, Exception {
        if (file == null || file.getOriginalFilename() == "") {
            return null;
        }
        UUID uuid = UUID.randomUUID(); // 랜덤 파일명 부여
        String fileName = uuid.toString() + "_" + file.getOriginalFilename();

        File copyFile = new File("\\\\192.168.10.51\\upload\\nestlibrary\\user\\" + email+ "\\" + fileName);
//        File copyFile = new File("\\\\http://192.168.10.51:8082/\\nestlibrary\\user\\" + email + "\\" + fileName);
        file.transferTo(copyFile);
        return fileName;
    }
    
    public void fileDelete(String file, String email) throws IllegalStateException, Exception {
        if (file != null) {
            String decodedString = URLDecoder.decode(file, StandardCharsets.UTF_8.name()); // 한글 디코딩 처리
            File f = new File("링크주소");
            f.delete();
        }
    }
}
