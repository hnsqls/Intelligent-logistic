package com.ls.upload.controller;

import com.ls.wuliu.common.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    @PostMapping("/uploadfile")
    public R upload(MultipartFile file) throws IOException {
        //接受文件--》传参即可

        //保存文件到本地，注意名字，不要一样回替换导致部分用户数据更该
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + file.getOriginalFilename();
        file.transferTo(new File("E:/Back-end-development/微服务/upload/" + filename));
        //将服务器上最终保存的文件真实路径和名字返回给前端
        return  R.success().setExtra(filename);
    }
}
