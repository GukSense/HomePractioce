package com.example.greengram.user;

import com.example.greengram.common.CustomFileUtils;
import com.example.greengram.user.model.SignInRes;
import com.example.greengram.user.model.SignUpPostReq;
import com.example.greengram.user.model.SingInPostReq;
import com.example.greengram.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;
    @Transactional
    public int userPost(@RequestPart MultipartFile pic, @RequestPart SignUpPostReq p) {
        //프로필 파일 업로드
        String saveFileName = customFileUtils.makeRandomFileName(pic);
        p.setPic(saveFileName);
        String hashpw = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashpw);
        int result = mapper.userPost(p);

        if(pic == null) {
            return result;
        }

        try {
            String path = String.format("user/%d", p.getUserId());
            customFileUtils.makeFolderName(path);
            String target = String.format("%s/%s", path, saveFileName);
            customFileUtils.transferTo(pic, target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 오류");
        }
        return result;
    }

    public SignInRes postSignIn(SingInPostReq p) {
        User findUser = mapper.getUserById(p.getUid());

        if (findUser == null) {
            throw new RuntimeException("아이디를 확인해 주세요.");
        }
        if(!BCrypt.checkpw(p.getUpw(), findUser.getUpw())) {
            throw new RuntimeException("비밀번호를 확인해 주세요.");
        }

        return SignInRes.builder()
                        .userId(findUser.getUserId())
                        .nm(findUser.getNm())
                        .pic(findUser.getPic())
                        .build();
    }

}
