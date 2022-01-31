package com.spring.boot.controller;



import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.StoreDTO;
import com.spring.boot.mapper.StoreService;
import com.spring.boot.util.MyUtil;

//import aboutpetStore.spring.boot.dto.StorejjimDTO;
//import aboutpetStore.spring.boot.mapper.StorejjimService;
//import aboutpetStore.spring.boot.util.MyUtil;

@Controller
public class StoreController {
	@Resource
	private StoreService storeService;
	
//	@Resource
//	private StorejjimService storejjimService;

	@Autowired
	MyUtil myUtil;


	//application.properties에서 설정한 내용주입

	  @Value("${fileDir}")
	  String uploadFileDir;


	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception{

		ModelAndView mav = new ModelAndView();
		mav.setViewName("stores/homeStore");
		return mav;
	}
	


	//created.action 부분
	@RequestMapping(value = "/insertProduct.action"
			,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView insertProduct(HttpServletRequest request) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		
		String userId = (String)session.getAttribute("userId");

		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userId);
		mav.setViewName("stores/insertProduct");
		return mav;
	}

	//created_ok.action부분
	@RequestMapping(value = "/insertProduct_ok.action"
			,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView insertProduct_ok(StoreDTO dto,HttpServletRequest request,@RequestPart List<MultipartFile>  files) throws Exception{

		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		int maxNum = storeService.maxNum();
		dto.setNum(maxNum + 1);
		int maxProductNum = storeService.maxProductNum();
		dto.setProductNum(maxProductNum+1);

//		String tag1=dto.getTag();
//		tag1 = tag1.substring(0,tag1.lastIndexOf(","));
//		dto.setTag(tag1);
		
		
		//fileName,destinationFileName을 누적해서 담아놓을 변수
		String fileUrl="";
		String fileName1="";
		String fileOriName1="";
		
		if(files.isEmpty()){
			storeService.insertData(dto);
		}else {
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				//파일의 확장자명(소문자)
				String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();

				File destinationFile;
				String destinationFileName;
				 fileUrl = "C:/sts-bundle/work/AboutPet_fin/src/main/resources/static/uploadFiles";

				//파일 객체 생성
				do { 
					destinationFileName = RandomStringUtils.randomAlphanumeric(16) + "." + fileNameExtension;
					destinationFile = new File(fileUrl+"/"+destinationFileName);
				} while (destinationFile.exists()); 

				destinationFile.getParentFile().mkdirs();

				file.transferTo(destinationFile);
				
				fileOriName1+=fileName+",";
				fileName1+=destinationFileName+",";
			}
			
			//쉼표로 누적되어 있는 값의 마지막 ',' 앞 까지의 값을 추출해 저장
			fileName1 = fileName1.substring(0,fileName1.lastIndexOf(","));
			fileOriName1 = fileOriName1.substring(0,fileOriName1.lastIndexOf(","));
			
			dto.setUserId(userId);
			dto.setFileUrl(fileUrl);
			dto.setFileName(fileName1);
			dto.setFileOriName(fileOriName1);
			
			storeService.insertData(dto);


		}
		
		//String dtoId = dto.getUserId();
		//mav.addObject("dtoId", dtoId);
		
		//mav.setViewName("stores/homeStore");
		mav.setViewName("redirect:/insertProduct.action");
		return mav;	
	}
	
	
	
	
	//list.action부분
	@RequestMapping(value = "/homeStore.action"
			,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView homeStores(HttpServletRequest request,StoreDTO dto) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		
		
		//int num = Integer.parseInt(request.getParameter("num"));
		//int total = Integer.parseInt(request.getParameter("total"));
		String userId = (String)session.getAttribute("userId");

		//넘어온 페이지 번호		
		String pageNum = request.getParameter("pageNum");

		int currentPage = 1;

		if(pageNum!=null){
			currentPage = Integer.parseInt(pageNum);
		}

		//검색----------------------------
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if(searchValue!=null){

			//넘어온 값이 GET방식이라면 인코딩,디코딩을 한다
			if(request.getMethod().equalsIgnoreCase("GET")){
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}

		}else{
			searchKey = "brand";
			searchValue = "";
		}


		//검색----------------------------

		//전체 데이터의 갯수
		int dataCount =  storeService.getDataCount(searchKey, searchValue);

		//한 페이지에 표시할 데이터 갯수

		int numPerPage = 6;

		//전체 페이지의 갯수

		int totalPage = myUtil.getPageCount(numPerPage, dataCount);


		//전체페이지의 갯수가 삭제로 인해 현재페이지보다 작아질경우

		if(currentPage>totalPage){
			currentPage = totalPage;
		}

		//db에서 가져올 데이터의 시작과 끝
		int start  = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;


		List<StoreDTO> lists = 
				storeService.getLists(start, end, searchKey, searchValue);
	
		//페이징 처리
		String param = "";
		if(!searchValue.equals("")){
			param = "?searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		String listUrl = "/homeStore.action" + param;


		String pageIndexList = 
				myUtil.pageIndexList(currentPage, totalPage, listUrl);

		//글보기 주소 만들기
		String articleUrl = "/storeArticle.action";

		if(param.equals("")){
			articleUrl += "?pageNum=" + currentPage;
		}else{
			articleUrl += param + "&pageNum=" + currentPage;
		}
		//dto=storeService.getReadData(num);
		int price = dto.getPrice();
		int discount = dto.getDiscount();
		int total = price-discount;



		ModelAndView mav = new ModelAndView();

		//포워딩 페이지에 넘길 데이터
//		mav.addObject("price", price);
//		mav.addObject("discount", discount);
//		mav.addObject("total", total);

		mav.addObject("userId", userId);
		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);

		mav.addObject("articleUrl", articleUrl);

		mav.setViewName("stores/homeStore");
		return mav;

	}
	
	
	
	
	
	
	@RequestMapping(value = "/tag_ok.action"
			,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tag_ok(HttpServletRequest request) throws Exception{
		
		//넘어온 페이지 번호		
		String pageNum = request.getParameter("pageNum");

		int currentPage = 1;

		if(pageNum!=null){
			currentPage = Integer.parseInt(pageNum);
		}
		String tags1 = request.getParameter("tags1");
		
		
		
		
		
		//전체 데이터의 갯수
		int dataCount =  storeService.getDataCount1(tags1);

		//한 페이지에 표시할 데이터 갯수

		int numPerPage = 4;

		//전체 페이지의 갯수

		int totalPage = myUtil.getPageCount(numPerPage, dataCount);


		//전체페이지의 갯수가 삭제로 인해 현재페이지보다 작아질경우

		if(currentPage>totalPage){
			currentPage = totalPage;
		}

		//db에서 가져올 데이터의 시작과 끝
		int start  = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;


		List<StoreDTO> lists1 = 
				storeService.getLists1(start, end, tags1);

		//페이징 처리
		String param = "";
		
		param = "?tags1=" + tags1;
			
		
		String listUrl = "/homeStore.action" + param;


		String pageIndexList = 
				myUtil.pageIndexList(currentPage, totalPage, listUrl);

		//글보기 주소 만들기
		String articleUrl = "/storeArticle.action";

		if(param.equals("")){
			articleUrl += "?pageNum=" + currentPage;
		}else{
			articleUrl += param + "&pageNum=" + currentPage;
		}



		ModelAndView mav = new ModelAndView();

		//포워딩 페이지에 넘길 데이터
		mav.addObject("lists", lists1);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);

		mav.addObject("articleUrl", articleUrl);

		mav.setViewName("stores/homeStore");
		return mav;

		
	}

		
	
	@RequestMapping(value = "/logout.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView logout(HttpServletRequest request) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;

	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	//article 부분
	@RequestMapping(value = "/storeArticle.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView article(HttpServletRequest request) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue,"UTF-8");
		}
		
		storeService.updateHitCount(num);
		StoreDTO dto = storeService.getReadData(num);
		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/homeStore.action?pageNum="+pageNum);
			return mav;
		}
		int price = dto.getPrice();		
		int discount = dto.getDiscount();
		int total = price-discount;


		dto.setDeliveryInfo(dto.getDeliveryInfo().replaceAll("\n", "<br>"));
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		String dtoId = dto.getUserId();
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stores/storeArticle");
		mav.addObject("dtoId", dtoId);
		mav.addObject("userId", userId);
		mav.addObject("total", total);
		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);

		mav.setViewName("stores/storeArticle");
		return mav;

	}
	
	//updated
	@RequestMapping(value = "/updatedArticle.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated(HttpServletRequest request) throws Exception{
		//int productNum = Integer.parseInt(request.getParameter("productNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue,"UTF-8");
		}
		StoreDTO dto = storeService.getReadData(num);
		

		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName( "redirect:/homeStore.action?pageNum=" + pageNum);
			return mav;
		}
		String param = "pageNum=" + pageNum;
		if(searchValue!=null) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		ModelAndView mav = new ModelAndView();

		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);
		mav.setViewName("stores/updatedStore");
		
		return mav;

	}
	
	//updated_ok
	@RequestMapping(value = "/updatedArticle_ok.action",method = RequestMethod.POST)
	public ModelAndView updated_ok(StoreDTO dto, HttpServletRequest request,@RequestPart List<MultipartFile>  files) throws Exception{
	
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		//for문 안에서 fileName이랑 destinationFileName을 누적해서 담을 변수
		String fileUrl="";
		String fileName1="";
		String fileOriName1="";
		
		if(files.isEmpty()){
			storeService.updateData(dto);
		}else {
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
				File destinationFile;
				String destinationFileName;
				
				fileUrl = "C:/sts-bundle/work/AboutPet_fin/src/main/resources/static/uploadFiles";

				//파일 객체 생성
				do { 
					destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
					destinationFile = new File(fileUrl+"/"+destinationFileName);
				} while (destinationFile.exists()); 

				destinationFile.getParentFile().mkdirs();

				file.transferTo(destinationFile);
				
				fileOriName1+=fileName+",";
				fileName1+=destinationFileName+",";
					
			}
			fileName1 = fileName1.substring(0,fileName1.lastIndexOf(","));
			fileOriName1 = fileOriName1.substring(0,fileOriName1.lastIndexOf(","));
			
			dto.setFileUrl(fileUrl);
			dto.setFileName(fileName1);
			dto.setFileOriName(fileOriName1);			
			storeService.updateData(dto);		
		}
	
		String param = "pageNum=" + pageNum;
		if(!searchValue.equals("")) {
			param += "&searchKey=" + searchKey+
					"&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/homeStore.action?" + param);
		return mav;	
	}
	

	@RequestMapping(value = "/deletedArticle.action",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleted(HttpServletRequest request) throws Exception{
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		storeService.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		if(searchValue!=null&&searchValue.equals("")) {
			param += "&searchKey=" + searchKey+
					"&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/homeStore.action?" + param);

		return mav;		
	}	
}
