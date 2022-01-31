package com.spring.boot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.FileSystem;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.CommunityCommentDTO;
import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.CommunityLikesDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.ZzimListDTO;
import com.spring.boot.mapper.CommunityService;
import com.spring.boot.util.FileManager;
import com.spring.boot.util.MyUtil;

@Controller
public class CommunityController {

	@Resource
	private CommunityService communityService;
	
	@Autowired
	MyUtil myUtil;
	FileManager fileManager;
	
	@Value("${fileDir}")
	String uploadFileDir;
	/*
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		return mav;
		
	}
	*/
	
	@RequestMapping(value = "created.action", method = RequestMethod.GET)
	public ModelAndView created(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)request.getSession();
		
		if((String)session.getAttribute("userId") == null || session.getAttribute("userId").equals("")) {
			
			mav.setViewName("redirect:/login.action");
			
			return mav;
			
		}
		
		String userId = (String)session.getAttribute("loginUser");
		
		mav.addObject("userId", userId);
		
		mav.setViewName("community/created");
		
		return mav;		
	}
	
	@RequestMapping(value = "created_ok.action", method = RequestMethod.POST)
	public ModelAndView created_ok(CommunityDTO dto, MultipartHttpServletRequest request, List<MultipartFile> files) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)request.getSession();
		
		String basePath = uploadFileDir + "upload/";

		MultipartFile file = request.getFile("files");
		
		dto.setSaveName(FileManager.doFileUpload(files ,file.getOriginalFilename(), basePath));
		
		String savePicture = FileManager.saveOriginalName(files, file.getOriginalFilename());
		
		dto.setSavePicture(savePicture.substring(0, savePicture.lastIndexOf(",")));

		int maxNum = communityService.maxNum_Commu();

		dto.setNum(maxNum + 1);
		
		dto.setIp(request.getRemoteAddr());
		
		dto.setLink("naver.com");
		
		communityService.insertData_Commu(dto);
		
		mav.setViewName("redirect:list.action");		
		
		return mav;		
	}
	
	@RequestMapping(value = "list.action", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView list(HttpServletRequest req) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		String pageNum = req.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		String searchValue = req.getParameter("searchValue");
		
		if(searchValue != null) {
			
			if(req.getMethod().equalsIgnoreCase("GET")){
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
				
				
			}
			
		}else {
			
			searchValue = "일상";
			
		}
		
		int dataCount = communityService.getDataCount_Commu(searchValue);
		
		int numPerPage = 3;
		
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage > totalPage) {
			
			currentPage = totalPage;
			
		}
		
		int start =(currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;		
		
		List<CommunityDTO> lists = communityService.getLists_Commu(searchValue, start, end);
		
		List<MypageDTO> lists2 = communityService.getUserLists();
		
		String param = "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		
		String listUrl = "/list.action" + param;
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action";
		
		if(param.equals("")){
			
			articleUrl += "?pageNum=" + currentPage;
			
		}else{
			
			articleUrl += param + "&pageNum=" + currentPage;
			
		}
		
		if( userId != null ) {
			
			List<CommunityLikesDTO> lists3 = communityService.getLikeLists(userId);
			List<ZzimListDTO> zlists = communityService.getZzimLists(userId);
			ListIterator<CommunityDTO> it = lists.listIterator();
			
			while(it.hasNext()) {

					CommunityDTO data = it.next();
					
					data.setCountComment(communityService.com_comment_count(data.getNum()));
					
					for(MypageDTO mdto : lists2) {

						if(data.getUserName().equals(mdto.getUserId())) {
							
							data.setProfile(mdto.getSaveName());
							data.setUserNick(mdto.getUserNick());
							
						}
						
					}
					
					for(ZzimListDTO zdto : zlists) {
						
						if(zdto.getNum() == data.getNum() && zdto.getCategoryName().equals("community") ) {
							
							data.setCheckzzim("true");
							break;
							
						}else {
							
							data.setCheckzzim("false");
							
						}
						
					}
					
					for(CommunityLikesDTO data2 : lists3) {
							
						if(data2.getCno() == data.getNum()) {
								
							data.setChecklike("true");
							break;
								
						}else {
								
						data.setChecklike("false");
								
						}
					
					}

			}

		}else {
			
			ListIterator<CommunityDTO> it = lists.listIterator();
			while(it.hasNext()) {
				
				CommunityDTO data = it.next();
				
				data.setCountComment(communityService.com_comment_count(data.getNum()));
				
				for(MypageDTO mdto : lists2) {
					
					if(data.getUserName().equals(mdto.getUserId())) {
						
						data.setProfile(mdto.getSaveName());
						data.setUserNick(mdto.getUserNick());
						
					}
					
				}
				
			}
			
		}
		
		mav.addObject("userId", userId);
		mav.addObject("lists", lists);
		mav.addObject("lists2", lists2);
		mav.addObject("totalPage", totalPage);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("currentPage", currentPage);
		
		mav.setViewName("community/list");
		
		return mav;
	}
	
	@RequestMapping(value = "/article.action" , method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView article(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		String pageNum = request.getParameter("pageNum");
		String searchValue = request.getParameter("searchValue");
		
		searchValue = URLDecoder.decode(searchValue, "UTF-8");
		
		communityService.updateHitCount_Commu(num);
		
		CommunityDTO dto = communityService.getReadData_Commu(num);
		
		if(dto == null) {
			
			mav.setViewName("redirect:/list.action");
			
			return mav;
			
		}
		
		dto.setContent(dto.getContent().replaceAll("\n", "<br/>"));
		
		String param = "pageNum=" + pageNum;
		param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		
		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("community/article");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/userPage.action" , method = {RequestMethod.GET})
	public ModelAndView comUserPage(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		String userName = request.getParameter("userName");
		
		MypageDTO mdto = communityService.getReadUserData(userName);
		
		int count = communityService.com_userCount(userName);
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		int numPerPage = 1;
		
		int totalPage = myUtil.getPageCount(numPerPage, count);
		
		if(currentPage > totalPage) {
			
			currentPage = totalPage;
			
		}
		
		int start =(currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;		
		
		String param = "";
		
		String listUrl = "/userPage.action?userName=" + userName;
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action";
		
		if(param.equals("")){
			
			articleUrl += "?pageNum=" + currentPage;
			
		}else{
			
			articleUrl += param + "&pageNum=" + currentPage;
			
		}
		
		List<CommunityDTO> lists = communityService.com_MyUserListData(userName, start, end);
		
		
		mav.addObject("userName", userName);
		mav.addObject("count",count);
		mav.addObject("lists",lists);
		mav.addObject("mdto", mdto);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", totalPage);
		mav.setViewName("community/comUserPage");
		
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/cancleLike.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int cancleLike(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		int maxNum = communityService.likesmaxNum();

		MypageDTO dto = communityService.getReadUserData(userId);
		
		String userNick = dto.getUserNick();
		
		CommunityLikesDTO ldto = new CommunityLikesDTO();
		
		ldto.setLno(maxNum + 1);
		ldto.setCno(no);
		ldto.setUserId(userId);
		ldto.setUserNick(userNick);

		communityService.communityLikes_delete(no, userId);
		
		communityService.downlikes(no);
		
		CommunityDTO Cdto = communityService.getReadData_Commu(no);
		
		return Cdto.getLikes();
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/saveLike.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int saveLike(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		int maxNum = communityService.likesmaxNum();

		MypageDTO dto = communityService.getReadUserData(userId);
		
		String userNick = dto.getUserNick();
		
		CommunityLikesDTO ldto = new CommunityLikesDTO();
	
		ldto.setLno(maxNum + 1);
		ldto.setCno(no);
		ldto.setUserId(userId);
		ldto.setUserNick(userNick);
		
		communityService.communityLikes_insert(ldto);
		
		communityService.uplikes(no);
		
		CommunityDTO Cdto = communityService.getReadData_Commu(no);
		
		System.out.println(Cdto.getLikes());
		
		return Cdto.getLikes();
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/cancleZzim.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int cancleZzim(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		int maxNum = communityService.com_zzimMaxNum();

		MypageDTO dto = communityService.getReadUserData(userId);
		
		ZzimListDTO zdto = new ZzimListDTO();
		
		zdto.setSnum(maxNum + 1);
		zdto.setNum(no);
		zdto.setUserId(userId);

		communityService.com_zzim_delete(no, userId);
		
		return 1;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/saveZzim.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int saveZzim(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		int maxNum = communityService.com_zzimMaxNum();

		MypageDTO dto = communityService.getReadUserData(userId);
		
		ZzimListDTO zdto = new ZzimListDTO();
		
		
		
		zdto.setSnum(maxNum + 1);
		zdto.setCategoryName("community");
		zdto.setNum(no);
		zdto.setUserId(userId);
		
		communityService.com_zzim_insert(zdto);

		return 1;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/commentList.action",method = {RequestMethod.POST,RequestMethod.GET})
	public List<CommunityCommentDTO> commentList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int communityNum = Integer.parseInt(req.getParameter("no"));
		
		List<CommunityCommentDTO> clists = communityService.getcom_Comment_Lists(communityNum);
		
		List<MypageDTO> mlists = communityService.getUserLists();
		
		ListIterator<CommunityCommentDTO> it = clists.listIterator();

		while(it.hasNext()) {
			
			CommunityCommentDTO data = it.next();
			
			for(MypageDTO data2 : mlists) {

				if(data.getUserId().equals(data2.getUserId())) {
					
					data.setProfile(data2.getSaveName());

				}else {

					
				}
				
			}
			
		}
				
		return clists;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/saveComment.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int saveComment(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int communityNum = Integer.parseInt(req.getParameter("no"));
		
		String contents = (String)req.getParameter("contents");
		
		CommunityCommentDTO dto = new CommunityCommentDTO();
		
		int comCommentMaxNum = communityService.com_commentMaxNum();
		
		MypageDTO dto2 = communityService.getReadUserData(userId);
		
		String userNick = dto2.getUserNick();
		
		dto.setNum(comCommentMaxNum + 1);
		dto.setUserId(userId);
		dto.setContents(contents);
		dto.setCommunityNum(communityNum);
		dto.setUserNick(userNick);
		
		communityService.com_comment_insert(dto);
		
		return 1;
		
	}
	
	

	@RequestMapping(value = "/myList.action" , method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView myList(HttpServletRequest req) throws Exception{	
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)req.getSession();
		String userId = (String)session.getAttribute("userId");
		
		int cnum = Integer.parseInt(req.getParameter("num"));
		
		CommunityDTO dto = communityService.getReadData_Commu(cnum);
		
		MypageDTO mdto = communityService.getReadUserData(dto.getUserName());
		
		if(userId.equals("") || userId == null) {
			
			
		}else {
			
		
		List<CommunityLikesDTO> lists = communityService.getLikeLists(userId);
		List<ZzimListDTO> zlists = communityService.getZzimLists(userId);
		
		for(ZzimListDTO zdto : zlists) {
			
			if(zdto.getNum() == dto.getNum() && zdto.getCategoryName().equals("community") ) {
				
				dto.setCheckzzim("true");
				break;
				
			}else {
				
				dto.setCheckzzim("false");
				
			}
			
		}		
		
		for(CommunityLikesDTO data2 : lists) {
			
			if(data2.getCno() == dto.getNum()) {
					
				dto.setChecklike("true");
				break;
					
			}else {
					
			dto.setChecklike("false");
					
			}
		
		}
		
		}
		
		
		mav.addObject("dto", dto);
		mav.addObject("mdto", mdto);
		mav.setViewName("community/myList");
		
		return mav;	
		
	}
	
	

	@RequestMapping(value= "/moreList.action",method = {RequestMethod.POST,RequestMethod.GET})
	public String moreList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		String pageNum = req.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null) {
			
			currentPage = Integer.parseInt(pageNum);
			
		}
		
		String searchValue = req.getParameter("searchValue");
		
		if(searchValue != null) {
			
			if(req.getMethod().equalsIgnoreCase("GET")){
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
				
				
			}
			
		}else {
			
			searchValue = "일상";
			
		}
		
		int dataCount = communityService.getDataCount_Commu(searchValue);
		
		int numPerPage = 3;
		
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage > totalPage) {
			
			currentPage = totalPage;
			
		}
		
		int start =(currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;		
		
		List<CommunityDTO> lists = communityService.getLists_Commu(searchValue, start, end);
		
		List<MypageDTO> lists2 = communityService.getUserLists();
		
		String param = "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		
		String listUrl = "/list.action" + param;
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action";
		
		if(param.equals("")){
			
			articleUrl += "?pageNum=" + currentPage;
			
		}else{
			
			articleUrl += param + "&pageNum=" + currentPage;
			
		}
		
		if( userId != null ) {
			
			List<CommunityLikesDTO> lists3 = communityService.getLikeLists(userId);
			List<ZzimListDTO> zlists = communityService.getZzimLists(userId);
			ListIterator<CommunityDTO> it = lists.listIterator();
			
			String chli = null;

			while(it.hasNext()) {

					CommunityDTO data = it.next();
					
					data.setCountComment(communityService.com_comment_count(data.getNum()));
					
					for(MypageDTO mdto : lists2) {
						
					
						
						if(data.getUserName().equals(mdto.getUserId())) {
							
							data.setProfile(mdto.getSaveName());
							data.setUserNick(mdto.getUserNick());
							
						}
						
					}
					
					for(ZzimListDTO zdto : zlists) {
						
						if(zdto.getNum() == data.getNum() && zdto.getCategoryName().equals("community") ) {
							
							data.setCheckzzim("true");
							break;
							
						}else {
							
							data.setCheckzzim("false");
							
						}
						
					}
					
					for(CommunityLikesDTO data2 : lists3) {
							
						if(data2.getCno() == data.getNum()) {
								
							data.setChecklike("true");
							break;
								
						}else {
								
						data.setChecklike("false");
								
						}
					
					}

			}

		}else {
			
			ListIterator<CommunityDTO> it = lists.listIterator();
			while(it.hasNext()) {
				
				CommunityDTO data = it.next();
				
				data.setCountComment(communityService.com_comment_count(data.getNum()));
				
				for(MypageDTO mdto : lists2) {
					
					if(data.getUserName().equals(mdto.getUserId())) {
						
						data.setProfile(mdto.getSaveName());
						
					}
					
				}
				
			}
			
		}
		
		req.setAttribute("userId", userId);
		req.setAttribute("lists", lists);
		req.setAttribute("lists2", lists2);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageIndexList", pageIndexList);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("articleUrl", articleUrl);
		
		mav.setViewName("community/list");
		
		return "community/moreList";
		
		
	}
	
	@RequestMapping(value = "/moreUserPage.action" , method = {RequestMethod.GET})
	public ModelAndView moreUserPage(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		String userName = request.getParameter("userName");
		
		MypageDTO mdto = communityService.getReadUserData(userName);
		
		int count = communityService.com_userCount(userName);
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		int numPerPage = 1;
		
		int totalPage = myUtil.getPageCount(numPerPage, count);
		
		if(currentPage > totalPage) {
			
			currentPage = totalPage;
			
		}
		
		int start =(currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;		
		
		String param = "";
		
		String listUrl = "/userPage.action?userName=" + userName;
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action";
		
		if(param.equals("")){
			
			articleUrl += "?pageNum=" + currentPage;
			
		}else{
			
			articleUrl += param + "&pageNum=" + currentPage;
			
		}
		
		List<CommunityDTO> lists = communityService.com_MyUserListData(userName, start, end);
		
		request.setAttribute("count",count);
		request.setAttribute("lists",lists);
		request.setAttribute("mdto", mdto);
		request.setAttribute("totalPage", totalPage);
		
		mav.setViewName("community/moreUserPage");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/successUser.action")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("stores/homeStore");
		return mav;
		
	}
	
	
}






















