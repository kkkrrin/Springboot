package com.spring.boot.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.TermsDTO;
import com.spring.boot.mapper.TermsService;

@Controller
public class TermsController {
	
	@Resource
	private TermsService termsService;
	
	@RequestMapping(value = "/termsadd.action", method = RequestMethod.GET)
	public ModelAndView termsadd() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("terms/add");
		return mav;
	}
	
	@RequestMapping(value = "/termsadd_ok.action")
	public ModelAndView termsadd_ok(TermsDTO dto, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		int maxNum = termsService.maxNum();
		dto.setT_num(maxNum + 1);
		termsService.insertData(dto);
		mav.setViewName("redirect:/termsadd.action");
		return mav;
	}
	
	@RequestMapping(value = "/termslist.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		TermsDTO dto = new TermsDTO();
		List<TermsDTO> list = termsService.getLists(dto);
		ModelAndView mav = new ModelAndView();
		request.setAttribute("list", list);
		mav.setViewName("terms/list");
		mav.addObject("dto",dto);
		return mav;
	}
	

}
