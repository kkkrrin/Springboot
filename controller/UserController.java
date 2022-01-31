package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.mapper.UserService;
import com.spring.boot.util.MyUtil;

@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Autowired
	MyUtil myUtil;
	
	@RequestMapping(value= "/addUser.action",method = RequestMethod.GET)
	public ModelAndView addUser() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("bbs/addUser");
		return mav;
		
		
		
	}
	
	@RequestMapping(value= "/addInfo.action",method = RequestMethod.GET)
	public ModelAndView addInfo() throws Exception{
		
		ModelAndView mav = new ModelAndView();
	
		
		
		
		mav.setViewName("bbs/addInfo");
		return mav;
		
		
		
	}
	
	//회원가입창에서 다음 누를때 작동
	@RequestMapping(value= "/addInfo_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView addInfo_ok(MypageDTO dto, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		int maxNum = userService.maxNum();
		
		dto.setNum(maxNum + 1);
		
		System.out.println(dto.getNum());
		System.out.println(dto.getUserId());
		
		userService.insertData(dto);
		
		HttpSession session = (HttpSession)req.getSession();
		
		session.setAttribute("userId", dto.getUserId());
		
		System.out.println(session.getAttribute("userId"));
		
		mav.setViewName("redirect:nextInfo.action");
		
		return mav;
		
	}

	//정보를 다 넣고 태그는 아이디에 따로 넣는 방식
	@RequestMapping(value= "/nextInfo.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView nextInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception{

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)req.getSession();
		
		session.getAttribute("userId");
		
		if(session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
		{

			mav.setViewName("redirect:");
			
		}
		
		mav.setViewName("bbs/nextInfo");
		return mav;
		
		
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/checkId.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int checkId(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String checkId = req.getParameter("checkId");
		
		int chack = userService.idCheck(checkId);
		return chack;
	}
	
	@ResponseBody
	@RequestMapping(value= "/checkNick.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int checkNick(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String checkNick = req.getParameter("checkNick");
		
		int chack = userService.nickCheck(checkNick);

		return chack;
	}
	
	@ResponseBody
	@RequestMapping(value= "/nextInfo_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public String nextInfo_ok(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String tagArray = req.getParameter("tagArray");
		
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		tagArray = tagArray.substring(0, tagArray.lastIndexOf(","));
		
		userService.updateTag(tagArray, userId);
		
		return "1";
		
	}

	@RequestMapping(value= "/login.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(MypageDTO dto, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		ModelAndView mav = new ModelAndView();	
		
		mav.setViewName("bbs/login");
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/login_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int login_ok(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		HttpSession session = req.getSession();
		
		System.out.println(req.getParameter("userId"));
		System.out.println(req.getParameter("userPwd"));
		
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		
		
		int check = userService.loginId(userId, userPwd);
		System.out.println(check);
		
		int result = 0;
		
		if(check == 1) {
			
			session.setAttribute("userId", userId);
			System.out.println(session.getAttribute("userId"));
			
			result = 1;

			
		}else {
			
			result = 2;

		}	
		
		return result;
		
	}
	
	@RequestMapping(value= "/findId.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findId() throws Exception{
		
		ModelAndView mav = new ModelAndView();	
		
		mav.setViewName("bbs/findId");
		return mav;
		
	}
		
	@ResponseBody
	@RequestMapping(value= "/findId_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public String findId_ok(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
	
		
//		System.out.println(req.getParameter("userId"));
//		System.out.println(req.getParameter("userPwd"));
		
		String userMail = req.getParameter("userMail");
		
		
		
		int check = userService.findChk(userMail);
				
		String userId= userService.findId(userMail);
		System.out.println(check);
		
		
		String result = "";
		
		if(check == 1) {
			
			
			result = userId;

			
		}else {
			
			result = "";
			

		}	
		
		return result;
		
	}
	
	@RequestMapping(value= "/findCompId.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findComplId(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String userId= req.getParameter("userId");
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("userId",userId);
		mav.setViewName("bbs/findCompId");
		return mav;
		
		
		
	}
	
	
	@RequestMapping(value= "/findPwd.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findPwd() throws Exception{
		
		ModelAndView mav = new ModelAndView();	
		
		mav.setViewName("bbs/findPwd");
		return mav;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value= "/findPwd_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findPwd_ok(HttpServletRequest request, HttpServletResponse resp) throws Exception{
		
	
		ModelAndView mav = new ModelAndView();
		
		String userId = request.getParameter("userId");
		String userMail = request.getParameter("userMail");
		
		String userPwd = userService.pwdChk(userId);
		
			
	
		mav.addObject("userPwd",userPwd);
		mav.setViewName("bbs/findCompPwd");
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value= "/findCompPwd.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findComplPwd(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
	
		ModelAndView mav = new ModelAndView();	
		
		
		
		
		
		mav.setViewName("bbs/findCompPwd");
		return mav;
		
		
		
	}
	

	@RequestMapping(value= "/findCompPwd_ok.action",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findComplPwd_ok(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
	
		ModelAndView mav = new ModelAndView();	
		
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("newPswd");
		
		userService.changePwd(userPwd, userId);
		
		
		
		
		mav.setViewName("bbs/login");
		return mav;
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
