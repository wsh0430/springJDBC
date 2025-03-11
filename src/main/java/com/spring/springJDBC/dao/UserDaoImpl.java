package com.spring.springJDBC.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.springJDBC.vo.UserVo;

@Component //service이외의 것들
public class UserDaoImpl implements UserDao {

	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	private String sql="";
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<UserVo> rowMapper = (rs, idx) -> {
		UserVo vo = new UserVo();
		vo.setIdx(rs.getInt("idx"));
		vo.setMid(rs.getString("mid"));
		vo.setPwd(rs.getString("pwd"));
		vo.setName(rs.getString("name"));
		vo.setAge(rs.getInt("age"));
		vo.setGender(rs.getString("gender"));
		vo.setAddress(rs.getString("address"));
		return vo;
	};
	
	@Override
	public int setUserInput(UserVo vo) {
		sql ="insert into user values (default,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, vo.getMid(), vo.getPwd(), vo.getName(), vo.getAge(), vo.getGender(), vo.getAddress());
		//select만 쿼리 이건 update
	}

	@Override
	public int getUserCnt() {
		sql = "select count(*) from user";
		return jdbcTemplate.queryForObject(sql, Integer.class);//select의므로 query, 리턴값이 정수이므로 Integer
	}

	@Override
	public UserVo getUserIdSearch(String mid) {
		sql = "select * from user where mid = ?";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, mid); //여러개 짜리라 에러가 남
		} catch (EmptyResultDataAccessException e) {
			return null; // 여러개 아니라 하나라고 강제로 주입
		}
		
	}
	
}
