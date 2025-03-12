package com.spring.springJDBC.dao;

import java.util.List;

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

	//아이디 중복 체크 후 검색한 결과를 돌려보냄(자료가 있으면 해당 레코드를, 없으면 ?를 돌려보냄)
	@Override
	public UserVo getUserIdSearch(String mid) {
		sql = "select * from user where mid = ?";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, mid); //자료가 없으면 Empty가 넘어감
		} catch (EmptyResultDataAccessException e) { //Empty : null이 아닌 공백같은 "환경", empty가 넘어가므로 예외처리 해야함
			return null; // Empty면 null을 반환
		}
		
	}

	
	//전체 조회처리
	@Override
	public List<UserVo> getUserList() {
		sql ="select * from user order by idx desc";
		return jdbcTemplate.query(sql, rowMapper);
 	}

	@Override
	public int setUserDeleteOk(int idx) {
		sql = "delete from user where idx = ?";
		return jdbcTemplate.update(sql, idx);//select는 쿼리 update,delete,insert는 update
	}

	@Override
	public UserVo getUserIdxSearch(int idx) {
		sql = "select * from user where idx = ?";
		
		return jdbcTemplate.queryForObject(sql, rowMapper, idx);
	}

	@Override
	public int getUserUpdate(UserVo vo) {
		sql = "update user set pwd=?, name=?, age=?, gender=?, address=? where idx = ?";
		return jdbcTemplate.update(sql, vo.getPwd(), vo.getName(), vo.getAge(), vo.getGender(), vo.getAddress(), vo.getIdx());
		
	}

	//회원검색(부분일치 검색)
	@Override
	public List<UserVo> userSearchListOk(String mid) {
		sql = "select * from user where mid like ?";
		return jdbcTemplate.query(sql, rowMapper, "%"+mid+"%"); //queryforobject는 1개만, qeury는 여러개 / mid에 부분일치라서 "%"붙여줌
	}

	@Override
	public UserVo getUserSearchPart(String part, String content) {
		sql = "";
		return null;
	}
	
}
