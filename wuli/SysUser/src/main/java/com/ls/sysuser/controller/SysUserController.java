package com.ls.sysuser.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.sysuser.pojo.R;
import com.ls.sysuser.pojo.SysUser;
import com.ls.sysuser.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
@Api(tags = "管理员模块相关接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("查询所有管理员信息")
    @ResponseBody
    @GetMapping("/sysuser")
    //前两个参数时分页，后4个参数时模糊搜索
    public R getAll(Integer pageNum,Integer pageSize,Integer id,String username,String nickname,String email){
//        R<SysUser> resp = new R<>();
//        resp.setCode(0);  
//        resp.setMsg("success");
//        resp.setData(sysUserService.selectAll());
//        return resp;

        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUsers = sysUserService.selectAll(id,username,nickname,email);//查询所有的，加上分页插件，主句sql不能以;结尾了
        //获取当前分页查询的信息，如多少条数据，当前页码，总共多少页，这些数据通过resp返回给前端
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        //所有查询的信息在data中，分页的信息在pageinfo中

        return R.success().setExtra(pageInfo);
    }

    @ApiOperation("登录- 根据username和password")
    @ResponseBody
    @PostMapping("/login")
    public R login(@RequestBody SysUser sysUser){
        List<SysUser> login = sysUserService.login(sysUser);

        if(login == null || login.isEmpty()){
            return R.error();
        }else{
            return R.success().setData(login);
        }

    }

    @ApiOperation("根据id删除管理员")
    @ResponseBody
    @DeleteMapping("/sysuser/{id}")
    public R deleteUser(@PathVariable("id") Integer id){
      sysUserService.deleteById(id);
      return R.success();
    }

    @ApiOperation("更新管理员信息-根据id更新昵称和邮箱")
    @ResponseBody
    @PostMapping("/sysuser")
    public R update(@RequestBody SysUser sysUser){
        System.out.println(sysUser);
        sysUserService.updateEmailAndNicknameById(sysUser);
        return R.success();

    }

    @ApiOperation("新增管理员")
    @ResponseBody
    @PutMapping("/sysuser")
    public R saveSysuser(@RequestBody SysUser sysUser){
        sysUserService.saveSysuser(sysUser);
        return R.success();

    }

}
