package com.ls.sysuser.dao;

import com.ls.sysuser.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    //
    List<SysUser> selectByUsernameAndPassword(SysUser sysUser);

    //
    List<SysUser>   select(@Param("id") Integer id,@Param("username") String username,@Param("nickname") String nickname,@Param("email") String email);

    //根据id删除
    int deleteById(Integer id);

    int updateEmailAndNicknameById(SysUser sysUser);
    int insertSysuser(SysUser sysUser);
}
