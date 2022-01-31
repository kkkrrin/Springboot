package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.boot.dto.InquiryDTO;
import com.spring.boot.dto.MyWishListDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.MypetDTO;

import com.spring.boot.dto.UserAddressDTO;
import com.spring.boot.dto.UserCardDTO;
import com.spring.boot.dto.UserzzimListDTO;
import com.spring.boot.mapper.MypageMapper;
import com.spring.boot.mapper.MypageService;

@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	private MypageMapper mypageMapper;


	@Override
	public void createUser(MypageDTO dto) throws Exception {
		mypageMapper.createUser(dto);
		
	}

	@Override
	public void insertData(MypageDTO dto) throws Exception {
		mypageMapper.insertData(dto);
		
	}

	@Override
	public int maxNum() throws Exception {
		return mypageMapper.maxNum();
	}

	@Override
	public MypageDTO getReadData(String userId) throws Exception {
		return mypageMapper.getReadData(userId);
	}

	@Override
	public void updateData(MypageDTO dto) throws Exception {
		mypageMapper.updateData(dto);
	}

	@Override
	public void insertPetData(MypetDTO dto) throws Exception {
		
		mypageMapper.insertPetData(dto);
	}

	@Override
	public int petmaxNum() throws Exception {
		return mypageMapper.petmaxNum();
	}

	@Override
	public MypetDTO getReadPetData(String userId) throws Exception {
		return mypageMapper.getReadPetData(userId);
	}

	@Override
	public int getPetCount(String userId) throws Exception {
		return mypageMapper.getPetCount(userId);
	}

	@Override
	public List<MypetDTO> getLists(String userId) throws Exception {
		return mypageMapper.getLists(userId);
	}

	@Override
	public void deleteData(String userId) throws Exception {
		mypageMapper.deleteData(userId);
	}

	@Override
	public void deletePetData(String userId) throws Exception {
		mypageMapper.deletePetData(userId);
		
	}

	@Override
	public MypetDTO getReadPetData2(String petName) throws Exception {
		return mypageMapper.getReadPetData2(petName);
	}


	@Override
	public void insertQuiryData(InquiryDTO dto) throws Exception {
		mypageMapper.insertQuiryData(dto);
		
	}

	@Override
	public int inquiryNum() throws Exception {
		return mypageMapper.inquiryNum();
	}

	@Override
	public int getInquiryCount(String userId) throws Exception {
		return mypageMapper.getInquiryCount(userId);
	}

	@Override
	public void updatePwd(String userId, String userPwd) throws Exception {
		mypageMapper.updatePwd(userId, userPwd);
		
	}

	@Override
	public void insertAddress(UserAddressDTO dto) throws Exception {
		mypageMapper.insertAddress(dto);
	}

	@Override
	public int addressNum() throws Exception {
		return mypageMapper.addressNum();
	}

	@Override
	public List<UserAddressDTO> getAddressList(String userId) throws Exception {
		return mypageMapper.getAddressList(userId);
	}

	@Override
	public int getAddressCount(String userId) throws Exception {
		return mypageMapper.getAddressCount(userId);
	}

	@Override
	public void deleteAddress(String addressAlias) throws Exception {
		mypageMapper.deleteAddress(addressAlias);
	}

	@Override
	public UserAddressDTO getReadAddressData(String userId, String addressAlias) throws Exception {
		return mypageMapper.getReadAddressData(userId, addressAlias);
	}

	@Override
	public List<InquiryDTO> getInquiryList(String userId) throws Exception {
		return mypageMapper.getInquiryList(userId);
	}

	@Override
	public void deleteInquiry(String userId, String inquiryContent) throws Exception {
		mypageMapper.deleteInquiry(userId, inquiryContent);
		
	}

	@Override
	public int cardmaxNum() throws Exception {
		return mypageMapper.cardmaxNum();
	}

	@Override
	public void insertCardData(UserCardDTO dto) throws Exception {
		mypageMapper.insertCardData(dto);
	}

	@Override
	public UserCardDTO getReadCardData(String userId, String cardNum) throws Exception {
		return mypageMapper.getReadCardData(userId, cardNum);
	}

	@Override
	public List<UserCardDTO> getCardList(String userId) throws Exception {
		return mypageMapper.getCardList(userId);
	}

	@Override
	public int getCardCount(String userId) throws Exception {
		return mypageMapper.getCardCount(userId);
	}

	@Override
	public void deleteCard(String userId, String cardNum) throws Exception {
		mypageMapper.deleteCard(userId, cardNum);
		
	}

	@Override
	public void updateAddress(String userId, String addressAlias) throws Exception {
		mypageMapper.updateAddress(userId, addressAlias);
		
	}

	@Override
	public void insertZzimData(UserzzimListDTO dto) throws Exception {
		mypageMapper.insertZzimData(dto);
		
	}

	@Override
	public int zzimmaxNum() throws Exception {
		return mypageMapper.zzimmaxNum();
	}


	@Override
	public List<UserzzimListDTO> getZzimList(String userId) throws Exception {
		return mypageMapper.getZzimList(userId);
	}

	@Override
	public int getZzimCount(String userId, String categoryName) throws Exception {
		return mypageMapper.getZzimCount(userId, categoryName);
	}

	@Override
	public void updateTag(String userTag, String userId) {
		mypageMapper.updateTag(userTag, userId);
		
	}



	@Override
	public void insertWishList(MyWishListDTO dto) throws Exception {
		mypageMapper.insertWishList(dto);
	}

	@Override
	public int wishListmaxNum() throws Exception {
		return mypageMapper.wishListmaxNum();
	}

	@Override
	public List<UserzzimListDTO> getZzimList2(String userId, String categoryName) throws Exception {
		// TODO Auto-generated method stub
		return mypageMapper.getZzimList2(userId, categoryName);
		
	}

	



}
