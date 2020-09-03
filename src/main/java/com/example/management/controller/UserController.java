package com.example.management.controller;



import com.example.management.mapper.UserMapper;
import com.example.management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller   //如何和页面对应，字符串拼接
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user_register")
    @RequestMapping("/user_register")
    public String user_reg(){
        return "user_register";
    }
    @RequestMapping("/adduser")

    public String adduser(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");



        User user =new User();
        user.setUsername(username);//初始化
        user.setPassword(password);

        User user2= userMapper.getuser(username);
        if(user2!=null){
            map.put("msg6","该用户名已被注册，请重新注册!!!");
            return "user_register";
        }else{
            userMapper.adduser(user);
            map.put("msg6","注册成功，跳转登录页面");
            return "userlogin";
        }



    }
    @RequestMapping("/getuser")
    public String getuser(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        User user = userMapper.getuser(username);
        if(user!=null){
            map.put("msg5","该用户名已存在");
            return "user_register";
        }else{
            map.put("msg5","恭喜您，该用户名可注册新账号!");
            return "user_register";
        }
    }

    @GetMapping("/userlogin")

    public String login(){
        return "userlogin";
    }
    @RequestMapping("/userlogin")
    public String userlogin(HttpServletRequest request,Map<String,Object> map){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginuser = userMapper.userlogin(username, password);

        if(loginuser!=null){
            map.put("msg7","the user "+loginuser+" login");
            return "select";
        }
        else{
            map.put("msg7","用户名或密码错误，请重试！");
            return "userlogin";
        }
    }


    @GetMapping("/deleteuser")

    public String delete_user(){
        return "delete";
    }

    @RequestMapping("/deleteuser")
    public String deleteuser(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        User getuser = userMapper.getuser(username);
        if(getuser!=null){
            userMapper.deleteuser(username);
            map.put("msg8","用户删除成功!");
            return "delete";
        }else{
            map.put("msg8","此用户不存在");
            return "delete";
        }
    }


    @GetMapping("/updateuser")
    public String upuser(){
        return "update";
    }
    @RequestMapping("/updateuser")
    public String update1(HttpServletRequest request,Map<String,Object> map){   //String来拼接字符串
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User getuser = userMapper.getuser(username);
        if(getuser!=null){
            userMapper.updateuser(username,password);
            map.put("msg9","密码更新成功!");
            return "userlogin";
        }else{
            map.put("msg9","此用户不存在");
            return "update";
        }
    }
    @RequestMapping("/select")
    public String select(){
        return "select";
    }
}