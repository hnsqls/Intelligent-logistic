package com.ls.sysuser.service.imp;

import com.ls.sysuser.dao.SysUserMapper;
import com.ls.sysuser.pojo.SysUser;
import com.ls.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImp implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> login(SysUser sysUser) {
        return sysUserMapper.selectByUsernameAndPassword(sysUser);
    }

    @Override
    public List<SysUser> selectAll(Integer id,String username,String nickname,String email) {

        return sysUserMapper.select(id,username,nickname,email);
    }

    @Override
    public int deleteById(Integer id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    public int updateEmailAndNicknameById(SysUser sysUser) {
        return sysUserMapper.updateEmailAndNicknameById(sysUser);
    }

    @Override
    public int saveSysuser(SysUser sysUser) {
        return sysUserMapper.insertSysuser(sysUser);
    }
}
