package com.moc.hellospringboot.controller;

import com.moc.hellospringboot.domain.User;
import com.moc.hellospringboot.pojo.LeeJSONResult;
import com.moc.hellospringboot.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by han on 2019/1/9.
 */

//@RestController注解能够使项目支持Rest
@RestController
public class HelloController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }

    @RequestMapping(value = "/getUserByGet",method = RequestMethod.GET)
    String getUserByGet(@RequestParam(value="userName") String userName){
        return "Hello " + userName;
    }

    //通过RequestMethod.POST表示请求需要时POST方式
    @RequestMapping(value = "/getUserByPost",method = RequestMethod.POST)
    String getUserByPost(@RequestParam(value = "userName")String userName){
        return "Hello " + userName;
    }

    //在入参设置@RequestBody注解表示接收整个报文体，这里主要用在接收整个POST请求中的json报文体，
    //目前主流的请求报文也都是JSON格式了，使用该注解就能够获取整个JSON报文体作为入参，使用JSON解析工具解析后获取具体参数
    @RequestMapping(value = "/getUserByJson",method = RequestMethod.POST)
    String getUserByJson(@RequestBody String data){
        return "Json is " + data;
    }

    @RequestMapping("/getUser")
    public LeeJSONResult getUser(){
        User u = new User();
        u.setName("Lucy2");
        u.setPassword("1234");
        u.setBirthday(new Date());
        u.setAge(20);
        return LeeJSONResult.ok(u);
    }

    @RequestMapping("/getResource")
    public LeeJSONResult getResource(){
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return LeeJSONResult.ok(bean);
    }


}
