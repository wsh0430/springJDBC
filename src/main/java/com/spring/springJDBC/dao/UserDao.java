package com.spring.springJDBC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.springJDBC.vo.UserVo;

@Repository
public interface UserDao {

	int setUserInput(UserVo vo);

	int getUserCnt();

	UserVo getUserIdSearch(String mid);

	List<UserVo> getUserList();

	int setUserDeleteOk(int idx);

	UserVo getUserIdxSearch(int idx);

	int getUserUpdate(UserVo vo);

	List<UserVo> userSearchListOk(String mid);

	UserVo getUserSearchPart(String part, String content);
	
	
	
}
