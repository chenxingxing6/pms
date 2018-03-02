package com.peace.pms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peace.pms.entity.User;
import com.peace.pms.service.impl.UserServiceImpl;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
//	@RequestMapping("/login")
//	public String login(User user,HttpServletRequest request){
//		User resultUser = userService.login(user);
//		HttpSession session = request.getSession();
//		if(resultUser==null){
//			session.setAttribute("user", user);
//			session.setAttribute("error", "用户名或密码错误");
//			return "redirect:/index.jsp";
//		}else{
//			session.setAttribute("username", user.getLogin_name());
//			System.out.println(user.getLogin_name());
//			return "redirect:/user/";
//		}
//	}

	@RequestMapping("/form")
	@ResponseBody
	public String login(User user,HttpServletRequest request){
		String other = request.getParameter("other");
		System.out.println(user.toString()+"==="+other);
		if ("root".equals(user.getUsername())&&"123456".equals(user.getPassword())){
			return "登录成功"+user.toString();
		}
		return "登录失败"+user.toString();
	}
}
