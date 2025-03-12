package com.spring.springJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springJDBC.dao.UserDao;
import com.spring.springJDBC.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int setUserInput(UserVo vo) {
		
		return userDao.setUserInput(vo); //메소드복붙 후 타입 지움, 위에 유저dao 복사
	}

	@Override
	public int getUserCnt() {
		
		return userDao.getUserCnt();
	}

	@Override
	public UserVo getUserIdSearch(String mid) {
		return userDao.getUserIdSearch(mid);
	}

	@Override
	public List<UserVo> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public int setUserDeleteOk(int idx) {
		return userDao.setUserDeleteOk(idx);
	}

	@Override
	public UserVo getUserIdxSearch(int idx) {
		
		return userDao.getUserIdxSearch(idx);
	}

	@Override
	public int getUserUpdate(UserVo vo) {
		
		return userDao.getUserUpdate(vo);
	}

	@Override
	public List<UserVo> userSearchListOk(String mid) {
		
		return userDao.userSearchListOk(mid);
	}

	@Override
	public UserVo getUserSearchPart(String part, String content) {
		
		return userDao.getUserSearchPart(part, content);
	}
	
}
