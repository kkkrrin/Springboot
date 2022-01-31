package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.spring.boot.dto.InquiryDTO;
import com.spring.boot.dto.MyWishListDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.MypetDTO;

import com.spring.boot.dto.UserAddressDTO;
import com.spring.boot.dto.UserCardDTO;
import com.spring.boot.dto.UserzzimListDTO;

@Mapper
public interface MypageMapper {
	
	//mypage
	public void createUser(MypageDTO dto) throws Exception;
	
	public void insertData(MypageDTO dto) throws Exception;
	
	public int maxNum() throws Exception;
	
	public MypageDTO getReadData(String userId) throws Exception;
	
	public void updateData(MypageDTO dto) throws Exception;
	
	public void updateTag(String userTag, String userId);
	
	
	public void deleteData(String userId) throws Exception;
	
	public void updatePwd(String userId,String userPwd) throws Exception; 
	
	//address
	public void insertAddress(UserAddressDTO dto) throws Exception; 
	
	public int addressNum() throws Exception;
	
	public List<UserAddressDTO> getAddressList(String userId) throws Exception;
	
	public int getAddressCount(String userId) throws Exception;
	
	public void deleteAddress(String addressAlias) throws Exception; 
	
	public UserAddressDTO getReadAddressData(String userId,String addressAlias) throws Exception;
	
	public void updateAddress(String userId,String addressAlias) throws Exception;
	
	//문의
	public void insertQuiryData(InquiryDTO dto) throws Exception;
	
	public int inquiryNum() throws Exception;
	
	public int getInquiryCount(String userId) throws Exception;
	
	public List<InquiryDTO> getInquiryList(String userId) throws Exception; 
	
	public void deleteInquiry(String userId,String inquiryContent) throws Exception;
	
	
	//mypet
	
	public void insertPetData(MypetDTO dto) throws Exception;
	
	public int petmaxNum() throws Exception;
	
	public MypetDTO getReadPetData(String userId) throws Exception;
	
	public int getPetCount(String userId) throws Exception;
	
	public List<MypetDTO> getLists(String userId) throws Exception;
	
	public void deletePetData(String userId) throws Exception;
	
	public MypetDTO getReadPetData2(String petName) throws Exception;
	
	
	//카드관리
	
	public int cardmaxNum() throws Exception;
	
	public void insertCardData(UserCardDTO dto) throws Exception;
	
	public UserCardDTO getReadCardData(String userId,String cardNum) throws Exception;
	
	public List<UserCardDTO> getCardList(String userId) throws Exception;
	
	public int getCardCount(String userId) throws Exception;
	
	public void deleteCard(String userId,String cardNum) throws Exception;
	
	
	//zzimList (test)
	
	public void insertZzimData(UserzzimListDTO dto) throws Exception;
	
	public int zzimmaxNum() throws Exception;
	
	public int getZzimCount(String userId, String categoryName) throws Exception;
	
	public List<UserzzimListDTO> getZzimList(String userId) throws Exception;
	
	
	//wishList (test) 
	
	
	public void insertWishList(MyWishListDTO dto) throws Exception; 
	
	public int wishListmaxNum() throws Exception;
	
	public List<UserzzimListDTO> getZzimList2(String userId, String categoryName) throws Exception;
	
	
}
