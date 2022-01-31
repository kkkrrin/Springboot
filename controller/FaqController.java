package com.spring.boot.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.FaqDTO;
import com.spring.boot.mapper.FaqMapper;
import com.spring.boot.mapper.FaqService;
import com.spring.boot.util.MyUtil;

@Controller
public class FaqController {
	
	@Resource
	private FaqService faqService;
	
	
	
//	@RequestMapping(value = "/")
//	public ModelAndView index() throws Exception{
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("index");
//		return mav;
//	}
	
	@RequestMapping(value="/faqadd.action", method = RequestMethod.GET)
	public ModelAndView faqadd() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("FAQ/add");
		return mav;
	}
	@RequestMapping(value="/faqadd_ok.action", method = RequestMethod.POST)
	public ModelAndView faqadd_ok(FaqDTO dto, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		int maxNum = faqService.maxNum();
		dto.setF_num(maxNum + 1);
		faqService.insertData(dto);
		mav.setViewName("redirect:/faqadd.action");
		return mav;
	}

	
	
	
	@RequestMapping(value = "/faqlist.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		
		FaqDTO dto = new FaqDTO();
		List<FaqDTO> list = faqService.getLists(dto);
		ModelAndView mav = new ModelAndView();
		request.setAttribute("list", list);
		mav.setViewName("FAQ/list");
		mav.addObject("dto",dto);
		return mav;
	}
    
	
	

}
