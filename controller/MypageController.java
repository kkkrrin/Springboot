package com.spring.boot.controller;


import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.AboutPatTvDTO;
import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.InquiryDTO;
import com.spring.boot.dto.MyWishListDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.MypageInfo;
import com.spring.boot.dto.MypetDTO;
import com.spring.boot.dto.MypetInfo;
import com.spring.boot.dto.UserAddressDTO;
import com.spring.boot.dto.UserAddressInfo;
import com.spring.boot.dto.UserCardDTO;
import com.spring.boot.dto.UserCardInfo;
import com.spring.boot.dto.UserzzimListDTO;
import com.spring.boot.mapper.AboutPatTvService;
import com.spring.boot.mapper.CommunityService;
import com.spring.boot.mapper.MypageService;
import com.spring.boot.util.FileManager;
import com.spring.boot.util.MyUtil;

@Controller
public class MypageController {
	
	@Resource
	private MypageService mypageService;
	
	@Resource
	private CommunityService communityService;
	
	@Resource
	private AboutPatTvService aboutpatTvService;
	
	@Autowired
	MyUtil myUtil;
	FileManager fileManager;
	
	@Value
	("${fileDir}")
	String uploadFileDir;
	 //주소 
	

	
	
	@RequestMapping(value = "/signup.do",method = RequestMethod.GET)
	public ModelAndView createUser() throws Exception{
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/signup");
		
		return mav; 
	}
	
	@RequestMapping(value = "/signup_ok.do",method = RequestMethod.POST )
	public ModelAndView postCreateUser(MypageDTO dto, HttpServletRequest request,MultipartHttpServletRequest mrequest, List<MultipartFile> files) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		 
		
		int maxNum=mypageService.maxNum();
		
		dto.setNum(maxNum+1);
		
		String basePath = uploadFileDir + "mypage/";

		MultipartFile file = mrequest.getFile("files");
		
		dto.setSaveName(FileManager.doFileUpload(files, file.getOriginalFilename(), basePath));
		
		dto.setOriginalName(FileManager.saveOriginalName(files, file.getOriginalFilename()));
		
		mypageService.insertData(dto);
		
		mav.setViewName("redirect:/");
		
		return mav;
		
	}
	
	
	@RequestMapping(value = "/mypage.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView mypage(MypetDTO petdto, HttpServletRequest request) throws Exception{
		
	
		HttpSession session =request.getSession(); 
		
		String userId = (String) session.getAttribute("userId");		
		
		if(userId==null) {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("bbs/login");
			return mav;
		}
		
		int TvCount = mypageService.getZzimCount(userId, "TV");
		int LogCount = mypageService.getZzimCount(userId, "community");
		int StoreCount = mypageService.getZzimCount(userId, "store");;
			
		MypageDTO dto=mypageService.getReadData(userId);
		
		List<MypetDTO> lists=mypageService.getLists(userId);
		
		int petCount=mypageService.getPetCount(userId);
		
		
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/mypage");
		mav.addObject("dto",dto);
		mav.addObject("TvCount",TvCount);
		mav.addObject("LogCount",LogCount);
		mav.addObject("StoreCount",StoreCount);
		mav.addObject("petCount",petCount);
		mav.addObject("lists",lists);
		
		return mav;
	}
	
	
	//수정 전 비밀번호 검증 
	@RequestMapping(value = "/updatecheck.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView infocheck(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/updatecheck");
		
		return mav; 
	
	}
	
	//비밀번호 수정 전 검증
	@RequestMapping(value = "/pwdcheck.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView pwdcheck(HttpServletRequest request) throws Exception{
	
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/pwdcheck");
		
		return mav; 
		
	}
	
	@RequestMapping(value = "/pwdcheck_ok.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView pwdcheck_ok(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userPwd=request.getParameter("userPwd");
		
		String userId = (String) session.getAttribute("userId");
		
		MypageDTO dto=mypageService.getReadData(userId);
			
		
		if(!dto.getUserPwd().equals(userPwd)) { 
			
			String cp=request.getContextPath();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer=response.getWriter();

			writer.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='"+ cp +"/pwdcheck.do';</script>");
			writer.close(); 
			
		}
		
		mav.setViewName("MyPage/updatepwd");
		
		return mav; 
	}
		
	@RequestMapping(value = "/pwdupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView pwdupdate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");

		String userPwd=request.getParameter("newPwd");
		String userId = (String) session.getAttribute("userId");
		
		mypageService.updatePwd(userId, userPwd);
		
		mav.setViewName("redirect:/mypage.do");
		 
		return mav;
		
	}
	
	
	
	
	//개인정보 수정
	@RequestMapping(value = "/update.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession();
		
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userPwd=request.getParameter("userPwd");
		
		String userId = (String) session.getAttribute("userId");
		
		MypageDTO dto=mypageService.getReadData(userId);
			
		
		if(!dto.getUserPwd().equals(userPwd)) { 
			
			String cp=request.getContextPath();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer=response.getWriter();

			writer.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='"+ cp +"/updatecheck.do';</script>");
			writer.close(); 
			
		}

	
		ModelAndView mav=new ModelAndView();
		mav.setViewName("MyPage/update");
		mav.addObject("dto",dto);
		
		
		return mav;
		 
	}
	
	//다음버튼누르면 나머지 먼저 업데이트되고 태그변경으로 넘어감 
	
	@RequestMapping(value = "/update_ok.do",method = RequestMethod.POST)
	public ModelAndView update_ok(MypageDTO dto,HttpServletRequest request,MultipartHttpServletRequest mrequest, List<MultipartFile> files) throws Exception{
	
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");

		String basePath = uploadFileDir + "mypage/";

		MultipartFile file = mrequest.getFile("files");
		
		dto.setSaveName(FileManager.doFileUpload(files, file.getOriginalFilename(), basePath));
		
		dto.setOriginalName(FileManager.saveOriginalName(files, file.getOriginalFilename()));		
		
		mypageService.updateData(dto);
		

		mav.setViewName("MyPage/updatenextInfo");
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/nextInfo_ok.do",method = {RequestMethod.POST,RequestMethod.GET})
	public String nextInfo_ok(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String tagArray = req.getParameter("tagArray");
		
		HttpSession session = (HttpSession)req.getSession();
		
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		String userId = (String) session.getAttribute("userId");
		
		tagArray = tagArray.substring(0, tagArray.lastIndexOf(","));
		
		mypageService.updateTag(tagArray, userId);
		
		return "1";
		
	}

	
	
	// 펫등록 순서
	@RequestMapping(value = "/petInsert.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView petInsert() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("MyPage/petCate");
		return mav;
		
		
		
	}
	
	@RequestMapping(value = "/petInsert1.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView petInsert1(HttpServletRequest request) throws Exception{
		
		String petCate=request.getParameter("petCate");
		
		HttpSession session=request.getSession();
		
		MypetInfo info=new MypetInfo();
		
		info.setPetCate(petCate);
		
		session.setAttribute("mypetInfo", info);
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("MyPage/petprofile");
		mav.addObject("petCate",petCate);
		mav.addObject("info",info);
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value = "/petInsert2.do",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView petInsert2(HttpServletRequest request,MultipartHttpServletRequest mrequest, List<MultipartFile> files) throws Exception{
		
		String petName=request.getParameter("petName");
		String petKind=request.getParameter("petKind");
		String petGender=request.getParameter("petGender");
		String petAge=request.getParameter("petAge");
		String petWeight=request.getParameter("petWeight");
		 
		//int petAge=Integer.parseInt(request.getParameter("petAge"));	
		//int petWeight=Integer.parseInt(request.getParameter("petWeight"));
		
		//String petImg=request.getParameter("petImg");
		//String saveName=request.getParameter("saveName");
		
		HttpSession session=request.getSession();
		

		String basePath = uploadFileDir + "mypage/";

		MultipartFile file = mrequest.getFile("files");
		
		MypetInfo info=(MypetInfo)session.getAttribute("mypetInfo");
		
		info.setPetName(petName);
		info.setPetKind(petKind);
		info.setPetGender(petGender);
		info.setPetAge(petAge);
		info.setPetWeight(petWeight);
		//info.setPetImg(petImg);
		info.setSaveName(FileManager.doFileUpload(files, file.getOriginalFilename(), basePath));
		info.setOriginalName(FileManager.saveOriginalName(files, file.getOriginalFilename()));
		
		session.setAttribute("mypetInfo", info);
		
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/petprofile2");
		mav.addObject("petName",petName);
		mav.addObject("info",info);
		
		
		return mav;
		
		
	}
	

	@RequestMapping(value = "/petInsert_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView petInsert_ok(MypetDTO dto, HttpServletRequest request) throws Exception{
		
		String petNeuter=request.getParameter("petNeuter");
		String petDisease=request.getParameter("petDisease");
		String petAllergy=request.getParameter("petAllergy");

		HttpSession session=request.getSession();

		MypetInfo info=(MypetInfo)session.getAttribute("mypetInfo");
		//MypageInfo mpinfo=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");	
		
		
		info.setPetNeuter(petNeuter);
		info.setPetDisease(petDisease);
		info.setPetAllergy(petAllergy);

		session.setAttribute("mypetInfo", info);
		
		int maxNum=mypageService.petmaxNum();
		 
		//MypetDTO dto= new MypetDTO();
		
		//dto.setUserId(userId.getUserId());
		dto.setUserId(userId);
		dto.setNum(maxNum+1);
		dto.setPetKind(info.getPetKind());
		dto.setPetCate(info.getPetCate());
		dto.setPetName(info.getPetName()); 
		dto.setPetGender(info.getPetGender());
		dto.setPetAge(info.getPetAge());
		dto.setPetWeight(info.getPetWeight());
		//dto.setPetImg(info.getPetImg());
		dto.setSaveName(info.getSaveName());
		dto.setOriginalName(info.getOriginalName());
		
		dto.setPetNeuter(petNeuter);
		dto.setPetAllergy(petAllergy); 
		dto.setPetDisease(petDisease);
		
		 
		mypageService.insertPetData(dto);
		
		ModelAndView mav=new ModelAndView();
	
		//마지막 펫등록 완료
		mav.setViewName("MyPage/petprofilereturn");
		mav.addObject("dto",dto);
		
		return mav;   
		
	}
	
	//펫정보
	@RequestMapping(value = "/petprofilereturn.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView petprofilereturn(HttpServletRequest request) throws Exception{
		
		HttpSession session=request.getSession();
		
		MypageInfo mpinfo=(MypageInfo)session.getAttribute("mypageinfo");
		String userId=mpinfo.getUserId(); 
		
		String petName=request.getParameter("petName");

		MypetDTO dto=new MypetDTO(); 
		
		dto=mypageService.getReadPetData2(petName);
		
		
		List<MypetDTO> lists=mypageService.getLists(userId);
		
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("MyPage/petprofilemainreturn");
		mav.addObject("lists", lists);
		
		mav.addObject("dto",dto);
	
		return mav; 
		
	}
		
	//건강수첩
	@RequestMapping(value = "/pethealthbook.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView pethealthbook(MypetDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("MyPage/pethealthbook");
 
		
		return mav; 
		
	}
		
	
	//탈퇴
	@RequestMapping(value = "/leavepet.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView leavepet(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("MyPage/leavepet");
		return mav;
		
	}
		
	@RequestMapping(value = "/leavepet_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView leavepet_ok(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session= request.getSession();
		
		MypetInfo pinfo=(MypetInfo)session.getAttribute("mypetInfo");
		MypageInfo mpinfo=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId=mpinfo.getUserId();
		
		mypageService.deleteData(userId);
		mypageService.deletePetData(userId);
		
		MypageDTO dto = mypageService.getReadData(userId);
	
		if(dto==null) {
			String cp=request.getContextPath();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer=response.getWriter();

			writer.println("<script>alert('회원이 탈퇴되었습니다.'); location.href='"+ cp +"/';</script>");
			writer.close(); 
			
		}
			
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
		
	}
	
	
	//배송지 등록
	 //배송지 메인
	@RequestMapping(value = "/addressInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addressInfo(UserAddressDTO dto,HttpServletRequest request) throws Exception{
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		UserAddressInfo info=(UserAddressInfo)session.getAttribute("useradinfo");
		MypageInfo mpinfo=(MypageInfo)session.getAttribute("mypageinfo");		
		
		String userId = (String) session.getAttribute("userId");
		
		List<UserAddressDTO> lists=mypageService.getAddressList(userId);
		
		int addressCount=mypageService.getAddressCount(userId);

		mav.setViewName("MyPage/addressinfo");
		mav.addObject("lists", lists);
		mav.addObject("count",addressCount);
		
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/createAddress.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createAddress(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("MyPage/createaddress");
		return mav; 

	}
	
	
	@RequestMapping(value = "/createAddress_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createAddress_ok(UserAddressDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		HttpSession session= request.getSession();
		
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		UserAddressInfo userAdInfo= new UserAddressInfo();
		
		int maxNum=mypageService.addressNum();
		String userId = (String) session.getAttribute("userId");
		
		String userName=request.getParameter("userName");
		String addressAlias=request.getParameter("addressAlias");
		String Region=request.getParameter("Region");
		String streetAddress=request.getParameter("streetAddress");
		String detailAddress=request.getParameter("detailAddress");
		String City=request.getParameter("City");
		String phoneNumber=request.getParameter("phoneNumber");
		
		dto.setNum(maxNum+1);
		dto.setUserId(userId);
		dto.setUserName(userName);
		dto.setAddressAlias(addressAlias);
		dto.setRegion(Region);
		dto.setStreetAddress(streetAddress); 
		dto.setDetailAddress(detailAddress);
		dto.setCity(City);
		dto.setPhoneNumber(phoneNumber);

		userAdInfo.setUserId(userId);
		userAdInfo.setUserName(userName);
		userAdInfo.setAddressAlias(addressAlias);
		userAdInfo.setRegion(Region);
		userAdInfo.setStreetAddress(streetAddress);
		userAdInfo.setDetailAddress(detailAddress);
		userAdInfo.setCity(City);
		userAdInfo.setPhoneNumber(phoneNumber);
		
		session.setAttribute("useradinfo",userAdInfo);
		
		mypageService.insertAddress(dto);
		
		mav.setViewName("redirect:/addressInfo.do");
		
		return mav; 
		
	}
	
	//배송지 삭제 addressAlias	
	@RequestMapping(value = "/deleteAddress.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteAddress(UserAddressDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session= request.getSession();
		
		String addressAlias=request.getParameter("addressAlias");
		
		mypageService.deleteAddress(addressAlias);
		
		mav.setViewName("redirect:/addressInfo.do");
		return mav; 

	}

	
	//문의하기 
	@RequestMapping(value = "/inquiry.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView inquiry(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();

		HttpSession session=request.getSession();	
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		String userId = (String) session.getAttribute("userId");
		
		 
		
		int inquiryCount=mypageService.getInquiryCount(userId);
		
		List<InquiryDTO> lists=mypageService.getInquiryList(userId);
		
		mav.setViewName("MyPage/inquiry");
		mav.addObject("inquiryCount",inquiryCount);
		mav.addObject("lists",lists);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/Createinquiry.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Createinquiry(HttpServletRequest request) throws Exception{
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("MyPage/createinquiry");
		
		return mav; 
		
	}
	

	@RequestMapping(value = "/Createinquiry_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Createinquiry_ok( HttpServletRequest request,MultipartHttpServletRequest mrequest, List<MultipartFile> files) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();	
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		String userId = (String) session.getAttribute("userId");
		
		InquiryDTO dto=new InquiryDTO();
		
		int maxNum=mypageService.inquiryNum();
		String inquiryCate =request.getParameter("inquiryCate");
		String inquiryContent=request.getParameter("inquiryContent");

		String basePath = uploadFileDir + "mypage/";

		MultipartFile file = mrequest.getFile("files");
		
		dto.setSaveName(FileManager.doFileUpload(files, file.getOriginalFilename(), basePath));		
		dto.setOriginalName(FileManager.saveOriginalName(files, file.getOriginalFilename()));
	
		dto.setNum(maxNum+1);
		dto.setUserId(userId);
		dto.setInquiryCate(inquiryCate);
		dto.setInquiryContent(inquiryContent);

		mypageService.insertQuiryData(dto);
		
		int inquiryCount=mypageService.getInquiryCount(userId);
		
		mav.setViewName("redirect:/inquiry.do");
		mav.addObject("dto",dto);
		mav.addObject("inquiryCount",inquiryCount);
		return mav;
	}
	
	
	//문의 글 삭제 
	@RequestMapping(value = "/deleteInquiry.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteInquiry(UserAddressDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		String userId=request.getParameter("userId");
		String inquiryContent=request.getParameter("inquiryContent");
		
		mypageService.deleteInquiry(userId, inquiryContent);
		//mypageService.deleteInquiry(userId);
		
		mav.setViewName("redirect:/inquiry.do");
		return mav;
		
	}
	
	
	 
	//카드메인
	@RequestMapping(value = "/cardinfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView cardinfo(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		UserCardInfo cdinfo=(UserCardInfo)session.getAttribute("usercardinfo");
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");
		
		int cardCount=mypageService.getCardCount(userId);
		
		List<UserCardDTO> lists=mypageService.getCardList(userId);
		

		mav.setViewName("MyPage/cardinfo");
		mav.addObject("lists",lists);
		mav.addObject("count",cardCount);
		
		return mav;
	}
	
	
	
	//카드 등록
	@RequestMapping(value = "/createCard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createCard(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
			
		
		mav.setViewName("MyPage/createcard");
		return mav;
	}
		
	
	@RequestMapping(value = "/createCard_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createCard_ok(UserCardDTO dto,HttpServletRequest request,MultipartHttpServletRequest mrequest, List<MultipartFile> files) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session = (HttpSession)request.getSession();
		MypageInfo mpinfo=(MypageInfo)session.getAttribute("mypageinfo");
		
		UserCardInfo info=new UserCardInfo();
		

		int maxNum=mypageService.cardmaxNum();
		
		
		String userId=mpinfo.getUserId();
		
		  String userName=request.getParameter("userName"); 
		  String cardName=request.getParameter("cardName"); 
		  String cardNum=request.getParameter("cardNum"); 
		  String cardValid=request.getParameter("cardValid");
		 
		
		  String basePath = uploadFileDir + "mypage/";

		MultipartFile file = mrequest.getFile("files");
		
		String saveName=FileManager.doFileUpload(files, file.getOriginalFilename(), basePath);
		String originalName=FileManager.saveOriginalName(files, file.getOriginalFilename());
		
		dto.setSaveName(saveName);
		dto.setOriginalName(originalName);
		dto.setNum(maxNum+1);
		dto.setUserId(userId);
		
		dto.setUserName(userName);
		dto.setCardName(cardName);
		dto.setCardNum(cardNum); 
		dto.setCardVaild(cardValid);
		
		 info.setUserId(userId);
		 info.setUserName(userName); 
		 info.setCardName(cardName);
		 info.setCardNum(cardNum); 
		 info.setCardValid(cardValid);
		 info.setSaveName(saveName);
		 info.setOriginalName(originalName);
		
		session.setAttribute("usercardinfo", info);
		
		mypageService.insertCardData(dto);
		
		mav.setViewName("redirect:/cardinfo.do");
		return mav;
	}
	
	//카드삭제
	@RequestMapping(value = "/deleteCard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteCard(UserCardDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session= request.getSession();
		
		String userId=request.getParameter("userId");
		String cardNum=request.getParameter("cardNum");
		
		mypageService.deleteCard(userId, cardNum);
		
		mav.setViewName("redirect:/cardinfo.do");
		return mav; 

	}
		
	//쿠폰
	@RequestMapping(value = "/mycoupon.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mycoupon(HttpServletRequest request) throws Exception{
		ModelAndView mav= new ModelAndView();
		
		mav.setViewName("MyPage/coupon");
		return mav;
		
	}

	
	// 각 카테고리(tv,log,store db명으로 데이터 읽어와야됨-?) 
	
	//zzim (테스트를 카드관리로 함/ 각 카테고리로 변경) 
	@RequestMapping(value = "/testStore.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testStore(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		UserCardInfo cdinfo=(UserCardInfo)session.getAttribute("usercardinfo");
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		
		int cardCount=mypageService.getCardCount(userId);
		
		List<UserCardDTO> lists=mypageService.getCardList(userId);
		
		String categoryName="STORE";
		
		//mav.setViewName("testStore");
		mav.addObject("lists1",lists);
		mav.addObject("count1",cardCount);
		mav.addObject("categoryName",categoryName);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/testLog.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testLog(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		UserCardInfo cdinfo=(UserCardInfo)session.getAttribute("usercardinfo");
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		
		int cardCount=mypageService.getCardCount(userId);
		
		List<UserCardDTO> lists=mypageService.getCardList(userId);
		
		String categoryName="Log";
		
		//mav.setViewName("testLog");
		mav.addObject("lists1",lists);
		mav.addObject("count1",cardCount);
		mav.addObject("categoryName",categoryName);
		
		return mav;
	}
	

	@RequestMapping(value = "/testTv.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testTv(HttpServletRequest request) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		
		UserCardInfo cdinfo=(UserCardInfo)session.getAttribute("usercardinfo");
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		
		int cardCount=mypageService.getCardCount(userId);
		
		List<UserCardDTO> lists=mypageService.getCardList(userId);
		
		String categoryName="TV";
		
		//mav.setViewName("testTv");
		mav.addObject("lists1",lists);
		mav.addObject("count1",cardCount);
		mav.addObject("categoryName",categoryName);
		
		return mav;
	}
	
	
		
	//찜버튼 클릭시 
	@RequestMapping(value = "/zzimonClick.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView zzimonClick(UserzzimListDTO dto,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		HttpSession session=request.getSession();
		
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		String userId = (String) session.getAttribute("userId");
		String categoryName=request.getParameter("categoryName"); 
		int num=Integer.parseInt(request.getParameter("num")); //고유 게시물num
		System.out.println(num); 
		
		
		int maxNum=mypageService.zzimmaxNum();
	
		dto.setSnum(maxNum+1);
		dto.setNum(num);
		dto.setUserId(userId);
		dto.setCategoryName(categoryName);
		
		
		//if dto에 같은 카테고리,같은num이 존재하면, delete(찜취소) 
		// 그렇지 않으면 insert
		
		
		mypageService.insertZzimData(dto);
		
		
		//mav.setViewName("redirect:/testStore.do");
		
		return mav;
	}
	
	
	
	
	//찜리스트된 항목 클릭
	@RequestMapping(value = "/MyzzimList.do", method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView myzzimList(HttpServletRequest request) throws Exception{
	      
	      ModelAndView mav= new ModelAndView();
	      HttpSession session=request.getSession();
	      
	      String categoryName = request.getParameter("categoryName");
	      
	      MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
	      
	      String userId = (String) session.getAttribute("userId");
	      
	      
	      List<UserzzimListDTO> zzimlists=mypageService.getZzimList2(userId, categoryName);
	      
	      
	      
	      if(categoryName.equals("TV")) {
	    	  
	    	  
	    	  String pageNum = request.getParameter("pageNum");
		      
		      int currentPage = 1;
		      
		      if(pageNum!=null) {
		         currentPage = Integer.parseInt(pageNum);
		      }
		      
		      String searchValue = request.getParameter("searchValue");
		     
		      
		      if(searchValue != null) {
		         
		         if(request.getMethod().equalsIgnoreCase("GET")){
		            searchValue = URLDecoder.decode(searchValue, "UTF-8");
		            
		            
		         }
		         
		      }else {
		         
		         searchValue = "";
		         
		      }
		      
	
		      int dataCount = aboutpatTvService.getDataCount(searchValue);
		      
		      int numPerPage = 3;
		      
		      int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		      
		      if(currentPage > totalPage) {
		         
		         currentPage = totalPage;
		         
		      }
		      
		      int start =(currentPage-1)*numPerPage+1;
		      int end = currentPage*numPerPage;      
		      
		      List<AboutPatTvDTO> vlist = aboutpatTvService.getLists2();
		      
		      ListIterator<AboutPatTvDTO> vit = vlist.listIterator();
		      while(vit.hasNext()) {
		         
		         AboutPatTvDTO cdata = vit.next();
		         
		         for(UserzzimListDTO zdto : zzimlists) {
		        	 
		            if(cdata.getNum() == zdto.getNum()) {
		               
		               cdata.setCheckMyZzimTV("true");
		               break;
		               
		            }
		            else {
		               
		               cdata.setCheckMyZzimTV("false");
		               
		            }
		            
		         }
		         
		      }
		      mav.addObject("vlist", vlist);
		      mav.setViewName("MyPage/zzimList");
	    	  
	    	 
	    	  
	    	  
	    	  
	    	  
	      }else if(categoryName.equals("community")) {
	    	  
	    	  String pageNum = request.getParameter("pageNum");
		      
		      int currentPage = 1;
		      
		      if(pageNum!=null) {
		         currentPage = Integer.parseInt(pageNum);
		      }
		      
		      String searchValue = request.getParameter("searchValue");
		      
		      if(searchValue != null) {
		         
		         if(request.getMethod().equalsIgnoreCase("GET")){
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
		      
		      List<CommunityDTO> clist = communityService.getLists_Commu(searchValue, start, end);
		      ListIterator<CommunityDTO> it = clist.listIterator();
		      
		      while(it.hasNext()) {
		         
		         CommunityDTO cdata = it.next();
		         
		         for(UserzzimListDTO zdto : zzimlists) {
		            
		            if(cdata.getNum() == zdto.getNum()) {
		               
		               cdata.setCheckMyZzim("true");
		               System.out.println(cdata.getCheckMyZzim());
		               break;
		               
		            }
		            else {
		               
		               cdata.setCheckMyZzim("false");
		               System.out.println(cdata.getCheckMyZzim());
		               
		            }
		            
		         }
		         
		      }
		      mav.addObject("clist", clist);
		      mav.setViewName("MyPage/zzimList");
	    	  
	      }else if(categoryName.equals("store")) {
	    	  
	    	  
	    	  
	    	  
	      }
	     
	      	
	      
	     
	      mav.addObject("zzimlists",zzimlists);
	      mav.addObject("categoryName",categoryName);
	      
	      return mav;
	      
	      
	   }
	
	
	
	
	// 스토어에서 장바구니 버튼을 클릭시 이게.. 여기서 하는게 의미가 잇나. 
	@RequestMapping(value = "/WishList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WishList(MyWishListDTO dto,HttpServletRequest request) throws Exception{
		ModelAndView mav= new ModelAndView();
		
		HttpSession session=request.getSession();
		MypageInfo info=(MypageInfo)session.getAttribute("mypageinfo");
		
		int num=Integer.parseInt(request.getParameter("num")); //고유 게시물num
		
		String userId = (String) session.getAttribute("userId");
		
		int maxNum=mypageService.wishListmaxNum();
		
		dto.setNum(num);
		dto.setUserId(userId);
		dto.setSnum(maxNum+1);
		
		mypageService.insertWishList(dto);
		

		mav.setViewName("redirect:/");
		return mav; 
		
		
	}
		
	
	
	
	
	
/*
	//-------------------------------------
	//file-test
	@RequestMapping(value = "/filetest.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView filetest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("filetest");
		return mav; 
		
	}
	
	//-------------------------------------
	@RequestMapping(value = "/filetest_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView filetest_ok(FileTestDTO dto,MultipartHttpServletRequest request, List<MultipartFile> files) throws Exception{
		
		ModelAndView mav= new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		
		String basePath = "C:/sts-bundle/work/Mypage/src/main/resources/static/upload/";

		MultipartFile file = request.getFile("files");
		
		dto.setSaveName(FileManager.doFileUpload(files, file.getOriginalFilename(), basePath));
		
		dto.setOriginalName(FileManager.saveOriginalName(files, file.getOriginalFilename()));
		
		mypageService.uploadFile(dto);
		
		mav.setViewName("redirect:/");		
		
		return mav;		 
	}
	//-------------------------------------
	@ResponseBody
	@RequestMapping(value = "/testjsp.do", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView testjsp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav= new ModelAndView();

		
		mav.setViewName("test");
		return mav;
	}
	*/
		
		
		
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	