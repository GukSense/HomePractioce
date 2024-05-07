package com.example.greengram.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
@Getter
@Slf4j
public class CustomFileUtils {
    private final String uploadPath;

    public CustomFileUtils(@Value("${file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
        log.info("uploagPath: {}", uploadPath);
    }

    //폴더 만들기
    public String makeFolderName(String path) {
        File folder = new File(uploadPath, path);
        folder.mkdirs();
        return folder.getAbsolutePath();
    }
    // UUID 랜덤 파일명
    public String getRandomName() {
        log.info("UUID.randomUUID: {}", UUID.randomUUID());
        return UUID.randomUUID().toString();
    }
    //파일명에서 확장자 추출
    public String getExt(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }
    //랜덤 파일명 with 확장자 만들기
    public String makeRandomFileName(String fileName) {
        return getRandomName() + getExt(fileName);
    }
    //using MultipartFile
    public String makeRandomFileName(MultipartFile mf) {
        String filename = mf.getOriginalFilename();
        return makeRandomFileName(filename);
    }
    //파일 저장(target: 경로/파일명)
    public void transferTo(MultipartFile mf, String target) throws Exception {
        File saveFile = new File(uploadPath, target);
        mf.transferTo(saveFile);
    }
}
