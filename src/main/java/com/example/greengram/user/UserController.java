package com.example.greengram.user;

import com.example.greengram.common.ResultDto;
import com.example.greengram.user.model.SignInRes;
import com.example.greengram.user.model.SignUpPostReq;
import com.example.greengram.user.model.SingInPostReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {
    private final UserService service;


    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 파일은 필수가 아님")
    public ResultDto<Integer> userPost(@RequestPart(required = false) MultipartFile pic, @RequestPart SignUpPostReq p) {
        //pw check length
        if(!checkLengthPw(p.getUpw())){throw new RuntimeException("비밀번호는 8~16 글자 입니다");}
        // id 값 영어,숫자만 받아오기
        String pattern = "^[a-zA-Z0-9]+$";
        boolean isMatch = p.getUid().matches(pattern);
        if (!isMatch) {throw new RuntimeException("ID는 영어대소문자,숫자만 가능합니다");}

        //유저프로필 사진
        int result = service.userPost(pic, p);


        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "로그인", description = "")
    public ResultDto<SignInRes>  postSignIn(@RequestBody SingInPostReq p) {
        log.info("p: {}", p);
        SignInRes result = service.postSignIn(p);

        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(result)
                .build();
    }

    private boolean checkLengthPw(String pw) {
        int pwLength = pw.length();

        if (7 < pwLength && pwLength < 17) {
            return true;
        }
        return false;
    }
}
