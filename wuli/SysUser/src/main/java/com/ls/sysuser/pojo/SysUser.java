package com.ls.sysuser.pojo;

import lombok.Data;

@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Boolean status;
    private String email;
//    private String login_date;
    private String photo;
}
