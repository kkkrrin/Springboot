package com.spring.boot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.spring.boot.dto.AboutPatTvDTO;
import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.CommunityLikesDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.VideoCommDTO;
import com.spring.boot.dto.ZzimListDTO;
import com.spring.boot.mapper.AboutPatTvService;
import com.spring.boot.mapper.CommunityService;
import com.spring.boot.util.FileManager;
import com.spring.boot.util.MyUtil;

@Controller
public class AboutPatTvContraller {

	@Resource
	private AboutPatTvService aboutpatTvService;
	
	@Resource
	private CommunityService communityService;

	@Autowired
	MyUtil myUtil;
	FileManager fileManager;

	@Value("${fileDir}")
	String uploadfileDir;

	// 동영상 부분

	// 컴퓨터 파일에서 영상 가져오기
	@GetMapping("/download")
	public StreamingResponseBody stream(HttpServletRequest request, @RequestParam("vidFileName") String vidFileName)
			throws Exception {
		File file = new File(uploadfileDir + vidFileName);
		final InputStream is = new FileInputStream(file);
		return os -> {
			readAndWrite(is, os);
		};
	}

	private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
		byte[] data = new byte[2048];
		int read = 0;
		while ((read = is.read(data)) > 0) {
			os.write(data, 0, read);
		}
		os.flush();
	}
	// 컴퓨터 파일에서 영상 가져오기----------------

	// 동영상 부분 --------------------------------
/*
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("index");
		return mav;
	}
*/
	@RequestMapping(value = "/TVcreated.action", method = RequestMethod.GET)
	public ModelAndView created(HttpServletRequest requset) throws Exception {

		ModelAndView mav = new ModelAndView();

		HttpSession session = (HttpSession)requset.getSession();
		
		
		String userId = (String)session.getAttribute("loginUser");
		
		mav.addObject("userId", userId);

		mav.setViewName("tv/created");

		return mav;
	}

	@RequestMapping(value = "TVcreated_ok.action", method = RequestMethod.POST)
	public ModelAndView created_ok(AboutPatTvDTO dto, MultipartHttpServletRequest request,
			List<MultipartFile> thumfiles, List<MultipartFile> videofiles) throws Exception {
		System.out.println("aaa7");

		ModelAndView mav = new ModelAndView();

		HttpSession session = (HttpSession) request.getSession();

		String basePath = uploadfileDir + "/video/";

		MultipartFile file1 = request.getFile("thumfiles");
		MultipartFile file2 = request.getFile("videofiles");

		dto.setThumFileName(FileManager.doThumFileUpload(thumfiles, file1.getOriginalFilename(), basePath));
		dto.setVidFileName(FileManager.doVidFileUpload(videofiles, file2.getOriginalFilename(), basePath));

		dto.setThumoriFileName(FileManager.saveThumOriginalName(thumfiles, file1.getOriginalFilename()));
		dto.setVidoriFileName(FileManager.saveVidOriginalName(videofiles, file2.getOriginalFilename()));

		/* doThumFileUpload(thumfiles, file1.getOriginalFilename(), basePath));
		 * for(MultipartFile file : thumfiles) {
		 * 
		 * String thumoriFileName = file.getOriginalFilename(); String filePath =
		 * basePath + "/" + thumoriFileName;
		 * 
		 * File dest1 = new File(filePath); file.transferTo(dest1);
		 * 
		 * fileName1 += thumoriFileName; dto.setThumoriFileName(thumoriFileName);
		 * 
		 * }
		 * 
		 * for(MultipartFile file : videofiles) { String vidoriFileName =
		 * file.getOriginalFilename(); String filePath = basePath + "/" +
		 * vidoriFileName;
		 * 
		 * File dest2 = new File(filePath); file.transferTo(dest2);
		 * 
		 * fileName2 += vidoriFileName; dto.setVidoriFileName(vidoriFileName); }
		 */

		System.out.println(basePath);
		System.out.println("aaa1");
		int maxNum = aboutpatTvService.maxNum();

		System.out.println("aaa2");
		dto.setNum(maxNum + 1);

		System.out.println("aaa3");
		aboutpatTvService.insertData(dto);

		System.out.println("aaa4");
		mav.setViewName("redirect:TVlist.action");
		System.out.println("aaa5");
		return mav;

	}

	@RequestMapping(value = "TVlist.action", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView list(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		HttpSession session = (HttpSession) request.getSession();

		String basePath = uploadfileDir + "/video/";

		// 유저로그인

		// 넘어온 페이지 번호
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		int currentPage = 1;

		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);

		}
		// 검색


		if (searchValue != null) {

			// 넘어온 값이 GET방식이라면 인코딩 디코딩를 해줘야한다.
			if (request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}

		} else {
			searchKey = "title";
			searchValue = "";

		}

		//
		int dataCount = aboutpatTvService.getDataCount(searchValue);

		int numPerPage = 20;

		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if (currentPage > totalPage) {

			currentPage = totalPage;
		}

		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;

		List<AboutPatTvDTO> lists = aboutpatTvService.getLists(searchKey, searchValue, start, end);

		
		String param = "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8");

		String listUrl = "/TVlist.action" + param;

		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

		String articleUrl = "/TVarticle.action";
		String catelistUrl = "/TVcatelist.action";
		String subcateUrl = "/TVsubcate.action";

		if (param.equals("")) {

			articleUrl += "?pageNum=" + currentPage;
			catelistUrl += "?pageNum=" + currentPage;
			subcateUrl += "?pageNum=" + currentPage;
		} else {

			articleUrl += param + "&pageNum=" + currentPage;
			catelistUrl += param + "&pageNum=" + currentPage;
			subcateUrl += param + "&pageNum=" + currentPage;
		}

		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("catelistUrl", catelistUrl);
		mav.addObject("subcateUrl", subcateUrl);
		mav.setViewName("tv/list");
		/*
		 * mav.addObject("dto", dto); mav.addObject("params", param);
		 * mav.addObject("pageNum", pageNum); mav.addObject("articleUrl", articleUrl);
		 * mav.setViewName("tv/catelist");
		 */
		return mav;
	}

	@RequestMapping(value = "/TVarticle.action", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView article(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = (HttpSession)request.getSession();

		int num = Integer.parseInt(request.getParameter("num"));
		
		String userId = (String)session.getAttribute("userId");

		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		searchValue = URLDecoder.decode(searchValue, "UTF-8");

		aboutpatTvService.updateHitCount(num);

		AboutPatTvDTO dto = aboutpatTvService.getReadData(num);

		if (dto == null) {
			mav.setViewName("redirect:TVlist.action");

			return mav;
		}
		
		 int currentPage = 1;

		dto.setVideoinfo(dto.getVideoinfo().replaceAll("\n", "<br/>"));

		String param = "pageNum" + pageNum;
		
		param += "&searchValue" + URLEncoder.encode(searchValue, "UTF-8");

		// article로 넘어가기----------------------------

		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);

		}

		String articleUrl = "/TVarticle.action";
		String subcateUrl = "/TVsubcate.action";

		if (param.equals("")) {

			articleUrl += "?pageNum=" + currentPage;
			subcateUrl += "?pageNum=" + currentPage;

		} else {

			articleUrl += "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8") + "&pageNum=" + currentPage;
			subcateUrl += "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8") + "&pageNum=" + currentPage;
		}
		
		// ----------------------------
		
		List<AboutPatTvDTO> lists3 = aboutpatTvService.getLists2();
		
		List<MypageDTO> lists4 = aboutpatTvService.getUserLists();
		
			if( userId != null ) {
			
			
			List<ZzimListDTO> zlists = aboutpatTvService.getTvZzimLists(userId);
			ListIterator<AboutPatTvDTO> vit = lists3.listIterator();
			
			
			
			String chli = null;

			while(vit.hasNext()) {

					AboutPatTvDTO data = vit.next();

						
					
					
					for(ZzimListDTO zdto : zlists) {
						
						if(zdto.getNum() == data.getNum() && zdto.getCategoryName().equals("community") ) {
							
							data.setCheckZzimTV("true");
							break;
							
						}else {
							
							data.setCheckZzimTV("false");
							
						}
						
					}
					

			}

		}else {
			
			ListIterator<AboutPatTvDTO> vit = lists3.listIterator();

			
		}


		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("subcateUrl", subcateUrl);

		mav.setViewName("tv/article");

		return mav;
	}

	@RequestMapping(value = "TVcatelist.action", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView catelist(AboutPatTvDTO dto, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		HttpSession session = (HttpSession) request.getSession();

		String basePath = uploadfileDir + "/video/";

		int num = Integer.parseInt(request.getParameter("num"));

		String pageNum = request.getParameter("pageNum");
		String searchValue = request.getParameter("searchValue");

		searchValue = URLDecoder.decode(searchValue, "UTF-8");

		aboutpatTvService.updateHitCount(num);

		dto = aboutpatTvService.getReadData(num);

		if (dto == null) {
			mav.setViewName("redirect:TVlist.action");

			return mav;
		}

		dto.setVideoinfo(dto.getVideoinfo().replaceAll("\n", "<br/>"));

		String param = "pageNum" + pageNum;
		param += "&searchValue" + URLEncoder.encode(searchValue, "UTF-8");

		// article로 넘어가기----------------------------
		int currentPage = 1;

		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);

		}

		String articleUrl = "/TVarticle.action";

		if (param.equals("")) {	

			articleUrl += "?pageNum=" + currentPage;

		} else {

			articleUrl += "?searchValue=" + URLEncoder.encode(searchValue, "UTF-8") + "&pageNum=" + currentPage;

		}
		// ----------------------------

		// list 기능-----
		


		int dataCount = aboutpatTvService.getDataCount(searchValue);

		int numPerPage = 20;

		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if (currentPage > totalPage) {

			currentPage = totalPage;
		}

		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;
		
		List<AboutPatTvDTO> allLists = aboutpatTvService.getAllList(start, end);
		
		// list 기능-----

		mav.addObject("dto", dto);
	    mav.addObject("allLists", allLists);
		mav.addObject("dataCount", dataCount);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);
		mav.addObject("articleUrl", articleUrl);
		mav.setViewName("tv/catelist");

		return mav;

	}

	@RequestMapping(value = "TVsubcate.action", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView subcate(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		HttpSession session = (HttpSession) request.getSession();

		String basePath = uploadfileDir + "/video/";

		int num = Integer.parseInt(request.getParameter("num"));

		String pageNum = request.getParameter("pageNum");
		String searchValue = request.getParameter("searchValue");

		searchValue = URLDecoder.decode(searchValue, "UTF-8");

		aboutpatTvService.updateHitCount(num);

		AboutPatTvDTO dto = aboutpatTvService.getReadData(num);

		if (dto == null) {
			mav.setViewName("redirect:TVlist.action");

			return mav;
		}

		dto.setVideoinfo(dto.getVideoinfo().replaceAll("\n", "<br/>"));

		String param = "pageNum" + pageNum;
		param += "&searchValue" + URLEncoder.encode(searchValue, "UTF-8");

		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);

		mav.setViewName("tv/subcate");

		return mav;
	}

	
	@ResponseBody
	@RequestMapping(value= "/cancleZzimTV.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int cancleZzim(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		
		int maxNum = aboutpatTvService.tv_zzimMaxNum();

		MypageDTO dto = aboutpatTvService.getReadUserData(userId);
		
		ZzimListDTO zdto = new ZzimListDTO();
		
		zdto.setSnum(maxNum + 1);
		zdto.setNum(no);
		zdto.setUserId(userId);

		aboutpatTvService.tv_zzim_delete(no, userId);
		
		return 1;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/saveZzimTV.action",method = {RequestMethod.POST,RequestMethod.GET})
	public int saveZzim(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	
		HttpSession session = (HttpSession)req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		int maxNum = aboutpatTvService.tv_zzimMaxNum();

		MypageDTO dto = aboutpatTvService.getReadUserData(userId);
		
		ZzimListDTO zdto = new ZzimListDTO();
		
		
		
		zdto.setSnum(maxNum + 1);
		zdto.setCategoryName("TV");
		zdto.setNum(no);
		zdto.setUserId(userId);
		
		aboutpatTvService.tv_zzim_insert(zdto);

		return 1;
		
	}
	
	 
	 
	  	
	 
}