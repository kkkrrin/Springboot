package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.BoardDTO;
import com.spring.boot.mapper.BoardMapper;
import com.spring.boot.mapper.BoardService;
import com.spring.boot.util.MyUtil;

@RestController
public class BoardController {
	
	@Resource
	private BoardService boardService; 
	
	@Autowired
	MyUtil myUtil;
	
	
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("index");
		
		return mav; 
		
	}

	
	@RequestMapping(value = "/created.action", method = RequestMethod.GET)
	public ModelAndView created() throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("bbs/created");
		
		return mav; 
		
	}
	
	
	@RequestMapping(value = "/created_ok.action", method = RequestMethod.POST)
	public ModelAndView created_ok(BoardDTO dto, HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		int maxNum = boardService.maxNum(); 
		
		dto.setNum(maxNum+1);
		dto.setIpAddr(request.getRemoteAddr());
		
		boardService.insertData(dto);
		
		mav.setViewName("redirect:/list.action");
		
		return mav; 
		
	}
	

	@RequestMapping(value = "/list.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		
		
		String pageNum = request.getParameter("pageNum");

		int currentPage = 1;

		if(pageNum!=null){

			currentPage = Integer.parseInt(pageNum);

		}

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if(searchValue != null){

		
			if(request.getMethod().equalsIgnoreCase("GET")){
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}

		}else{

			searchKey = "subject";
			searchValue = "";

		}


		
		int dataCount = boardService.getDataCount(searchKey, searchValue);

		
		int numPerPage = 3;

		
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

	
		if(currentPage > totalPage){

			currentPage = totalPage;

		}

	//db에서 가져올 데이터의 시작과 끝 
		int start =(currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;

		List<BoardDTO> lists = boardService.getLists(start, end, searchKey, searchValue);

		
		//페이징처리
		String param = "";
		if(!searchValue.equals("")){
			param = "?searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");

		}


		String listUrl ="/list.action" + param;
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

		
		//글보기 주소 만들기 
		String articleUrl = "/article.action";

		if(param.equals("")){

			articleUrl += "?pageNum=" + currentPage;

		}else{

			articleUrl += param + "&pageNum=" + currentPage;

		}
		
		
		ModelAndView mav = new ModelAndView();
		
	
		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		mav.addObject("articleUrl", articleUrl);
			
		mav.setViewName("bbs/list");
		
		return mav; 
		
		
		
	}
		
	

	@RequestMapping(value = "/article.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView article(HttpServletRequest request) throws Exception{
		
		
		int num= Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String searchKey=request.getParameter("searchKey");
		String searchValue=request.getParameter("searchValue");

		if(searchValue!=null) {
			searchValue=URLDecoder.decode(searchValue,"UTF-8");
		}

		boardService.updateHitCount(num); 
		BoardDTO dto=boardService.getReadData(num);

		if(dto==null) {
			
			ModelAndView mav = new ModelAndView(); 
			mav.setViewName("redirect:/list.action?pageNum="+pageNum);
			return mav;
		}

		int lineSu= dto.getContent().split("\n").length; 

		dto.setContent(dto.getContent().replace("\n", "<br/>")); 

		String param = "pageNum="+pageNum;

		if(searchValue!=null) {
			param+="&searchKey="+ searchKey;
			param+="&searchValue="+ URLEncoder.encode(searchValue,"UTF-8");
		}

		
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("bbs/article");
		mav.addObject("dto",dto);
		mav.addObject("params",param);
		mav.addObject("lineSu",lineSu);
		mav.addObject("pageNum",pageNum);
		
		return mav; 
		
		
	}
	
	
	

	@RequestMapping(value = "/updated.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updated(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");

		String searchKey=request.getParameter("searchKey");
		String searchValue=request.getParameter("searchValue");

		if(searchValue!=null) {
			searchValue=URLDecoder.decode(searchValue,"UTF-8");
		}

		BoardDTO dto= boardService.getReadData(num);

		if(dto==null) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			return mav; 
		}

		String param="pageNum=" +pageNum;

		if(searchValue!=null) {
			param+="&searchKey="+ searchKey + "&searchValue="+URLEncoder.encode(searchValue,"UTF-8");
		}

		
		ModelAndView mav = new ModelAndView(); 
		
		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);

		mav.setViewName("bbs/updated");
	
		return mav; 
	}
		
	

	@RequestMapping(value = "/updated_ok.action", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updated_ok(BoardDTO dto, HttpServletRequest request) throws Exception{
		

		String pageNum=request.getParameter("pageNum");
		String searchKey=request.getParameter("searchKey");
		String searchValue=request.getParameter("searchValue");

		boardService.updateData(dto);

		String param="pageNum=" +pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param+="&searchKey="+ searchKey + "&searchValue="+URLEncoder.encode(searchValue,"UTF-8");
		}
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav; 
		
		
		
	}
		

	@RequestMapping(value = "/deleted.action", method = RequestMethod.GET)
	public ModelAndView deleted(HttpServletRequest request) throws Exception{
		
		int num= Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String searchKey=request.getParameter("searchKey");
		String searchValue=request.getParameter("searchValue");

		boardService.deleteData(num);

		String param="pageNum=" +pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param+="&searchKey="+ searchKey + "&searchValue="+URLEncoder.encode(searchValue,"UTF-8");
		}
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav; 
		
	}
	
	
	
	
	
}
