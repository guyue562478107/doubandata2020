package com.team01.douban.controller;


import com.team01.douban.entity.User;
import com.team01.douban.service.UserService;
import com.team01.douban.tools.Message;
import com.team01.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@RequestMapping("/logic/user")
@Controller
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService service;



    @RequestMapping(value = "/login")
    public String login(String uid, String pwd){

        if (service.login(uid, pwd) ==1)
            return "redirect:/html/mainPage.html";

        else if (service.login(uid,pwd) == 2) {
            loginuid();

        }
        else if (service.login(uid, pwd)==3) {
            loginpwd();

        }
        return "redirect:/html/login.html";

    }

    @ResponseBody
    @RequestMapping(value = "/login uid",produces = "text/html")
    public String  loginuid(){
        return "<script>parent.alert('密码输入错误');</script>";
    }

    @ResponseBody
    @RequestMapping(value = "/login pwd",produces = "text/html")
    public String loginpwd(){
        return "<script>parent.alert('用户名不存在');</script>";
    }



    @RequestMapping(value = "/add")
    @ResponseBody
    public User addUser(User user){
        User newUser = service.addUser(user);
        return newUser;
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model){

        model.addAttribute("currentUser",null);

        return "redirect:/html/login.html";
    }

    @RequestMapping(value = "/currentUser")
    @ResponseBody
    public User currentUser(Model model){
        User currentUser = (User)model.getAttribute("currentUser");   //把拿到的currentUser名字做(User)类型转换

        if (currentUser != null)
            return currentUser;

        return null;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(User user, int page, int limit) {

        TableData data = new TableData();
        List<User> result = service.search(user, page, limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchCount(user));
        data.setData(result);

        return data;
    }

    @RequestMapping(value = "/pwdreset")
    @ResponseBody
    public Message pwdReset(int id){

        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if (service.resetPwd(id)){
            msg.setError(false);
            msg.setContent("密码已更新");
        }
        return msg;
    }
}
