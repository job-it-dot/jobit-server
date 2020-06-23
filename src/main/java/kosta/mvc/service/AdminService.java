package kosta.mvc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import kosta.mvc.domain.Companys;
import kosta.mvc.domain.Perchase;
import kosta.mvc.domain.Users;

public interface AdminService {
	/**
	 * 유저회원 조회
	 */
	List<Users> selectUser() throws IOException;
	
	/**
	 * 유저회원 id로검색
	 */
	Users selectById(long userId) throws IOException;
	
	/**
	 * 기업회원 조회
	 */
	List<Companys> selectCompany() throws IOException;
	
	/**
	 * 기업회원 id로검색
	 */
	Companys selelctCompanyId(long CompanyId) throws IOException;
	
	
	/**
	 * 기업 강제탈퇴
	 */
	int companysWithdrawal(long companyId) throws NotFoundException, IOException;
	
	
	/**
	 * 유저 강제탈퇴
	 */
	int memberWithdrawal(long memberId) throws NotFoundException, IOException;
	
	/**
	 * 기업회원 가입 승인
	 */
	int companyApproval(long companyId) throws IOException;
	
	/**
	 * 매출통계 조회
	 */
	List<Perchase> salesSelect() throws IOException;
	

	

}
