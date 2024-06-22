package com.ls.sysuser.service;

import com.ls.sysuser.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 登录
     * @param sysUser
     * @return
     */
    List<SysUser> login(SysUser sysUser);


    List<SysUser> selectAll(Integer id,String username,String nickname,String email);

    int deleteById(Integer integer);

    int updateEmailAndNicknameById(SysUser sysUser);


    int saveSysuser(SysUser sysUser);


}
