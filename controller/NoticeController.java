package com.spring.boot.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.NoticeDTO;
import com.spring.boot.mapper.NoticeService;

@Controller
public class NoticeController {
	
	@Resource
	private NoticeService noticeService;
	
	@RequestMapping(value = "/noticeAdd.action", method = RequestMethod.GET)
	public ModelAndView noticeAdd() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/add");
		return mav;
	}
	
	@RequestMapping(value = "/noticeAdd_ok.action", method = RequestMethod.POST)
	public ModelAndView noticeAdd(NoticeDTO dto, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		int maxNum = noticeService.maxNum();
		dto.setN_num(maxNum + 1);
		noticeService.insertData(dto);
		mav.setViewName("redirect:/noticeAdd.action");
		return mav; 
	}
	
	@RequestMapping(value = "/noticelist.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		NoticeDTO dto = new NoticeDTO();
		
		List<NoticeDTO> list = noticeService.getLists(dto);
		
		ModelAndView mav = new ModelAndView();
		
		request.setAttribute("list", list);
		mav.setViewName("notice/list");
		mav.addObject("dto",dto);
		return mav;
	}

}
