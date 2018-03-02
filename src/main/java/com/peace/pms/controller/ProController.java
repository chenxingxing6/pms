package com.peace.pms.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import com.peace.pms.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 读取配置文件（此文件与第三方登录代码无关）
 * @author 
 * kaptcha+spring mvc
 */
@Controller
public class ProController {
    @Autowired
    private Properties properties;

    @RequestMapping("/cxx")
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        System.out.println(properties.getProductName());
    }
}
