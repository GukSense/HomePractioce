package com.example.greengram.user;

import com.example.greengram.user.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    int userPost(SignUpPostReq p);
    User getUserById(String p);

}
