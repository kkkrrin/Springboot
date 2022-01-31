package com.spring.boot.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.JjimDTO;
import com.spring.boot.mapper.JjimService;
import com.spring.boot.util.MyUtil;

@Controller
public class JjimController {
	
	@Resource
	private JjimService jjimService;
	
	@Autowired
	MyUtil myUtil;
	
	/*
	  @RequestMapping(value = "/") public ModelAndView index() throws Exception{
	  
	  ModelAndView mav = new ModelAndView();
	  
	  mav.setViewName("index"); return mav;
	  
	  }
	 */
	
	@RequestMapping(value = "/jlist.action",
            method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView jlist(HttpServletRequest request,JjimDTO dto) throws Exception{

        int productNum = Integer.parseInt(request.getParameter("productNum"));
        String productName = (String)request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));

        dto.setProductNum(productNum);
        dto.setProductName(productName);
        dto.setPrice(price);
        

        jjimService.insertData_jjim(dto);
        
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("index");
        return mav;


    }
	
	  @RequestMapping(value = "/jArticle.action"
              ,method = {RequestMethod.GET,RequestMethod.POST})
      public ModelAndView jArticle(HttpServletRequest request,JjimDTO dto) throws Exception{
          HttpSession session = (HttpSession)request.getSession();


          //넘어온 페이지 번호
          String pageNum = request.getParameter("pageNum");

          int currentPage = 1;

          if(pageNum!=null){
              currentPage = Integer.parseInt(pageNum);
          }


          //검색----------------------------

          //전체 데이터의 갯수
          int dataCount =  jjimService.getDataCount_jjim();

          //한 페이지에 표시할 데이터 갯수

          int numPerPage = 3;

          //전체 페이지의 갯수

          int totalPage = myUtil.getPageCount(numPerPage, dataCount);


          //전체페이지의 갯수가 삭제로 인해 현재페이지보다 작아질경우

          if(currentPage>totalPage){
              currentPage = totalPage;
          }

          //db에서 가져올 데이터의 시작과 끝
          int start  = (currentPage-1)*numPerPage+1;
          int end = currentPage * numPerPage;


          List<JjimDTO> lists1 = 
                  jjimService.getLists_jjim(start, end);


          String listUrl = "/jArticle.action";


          String pageIndexList = 
                  myUtil.pageIndexList(currentPage, totalPage, listUrl);

          //글보기 주소 만들기
          String articleUrl1 = "/testArticle.action";
          articleUrl1 += "?pageNum=" + currentPage;



          ModelAndView mav = new ModelAndView();


          mav.addObject("lists1", lists1);
          mav.addObject("pageIndexList", pageIndexList);
          mav.addObject("dataCount", dataCount);
          mav.addObject("articleUrl1", articleUrl1);

          mav.setViewName("stores/jArticle");
          return mav;

      }
	
	
	
	

}
