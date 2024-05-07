package com.example.greengram.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomFileUtilsTest {
    @Autowired
    CustomFileUtils customFileUtils;

    @Test
    void makeFolderName() {
        String result = customFileUtils.makeFolderName("test12");
        System.out.println("result: " + result);
    }

}