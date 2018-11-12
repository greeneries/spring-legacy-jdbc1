package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
//	Row(���̺� ��) ������ <Emp> ��ü�� Mapper(����) �ϱ� ���� ����
//	��� Į���� �����͸� ��� ��������� �־�� �ϴ��� �˷��ִ� ����
	private RowMapper<Emp> rowMapper = new RowMapper<Emp>() {
		@Override
		public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
			Emp e = new Emp();
			e.setEmpno(rs.getInt("empno"));
			e.setEname(rs.getString("ename"));
			e.setJob(rs.getString("job"));
			e.setSal(rs.getDouble("sal"));
			
			return e;
		}
	};	
	
//	�� �����̳ʿ� JdbcTemplate�� ���� �� ��� DataSource�� �޾Ƽ� ó���ϴ� ���
//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
	
//	�޼ҵ帶�� �ݺ������� ���Ǵ� �κ��� ������ ������ ����� ����� ����.
	@Override
	public int insert(Emp emp) {
		String sql = "insert into EMP9(empno, ename, job, sal) values(?, ?, ?, ?)";
		int affected = jdbcTemplate.update(sql, 
				emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getSal());
		
		return affected;	
	}

	@Override
	public int update(Emp emp) {
		String sql = "update EMP9 set ename=?, job=?, sal=? where empno=?";
		return jdbcTemplate.update(sql, 
				emp.getEname(), emp.getJob(), emp.getSal(), emp.getEmpno());
	}

	@Override
	public int delete(int empno) {
		String sql = "delete EMP9 where empno=?";
		return jdbcTemplate.update(sql, empno);
	}

	@Override
	public List<Emp> findAll() {
		String sql = "select empno, ename, job, sal from EMP9 order by empno asc";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int count() {
		String sql = "select count(*) from EMP9";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Emp findOne(int empno) {
		String sql = "select empno, ename, job, sal from EMP9 where empno=?";
		return jdbcTemplate.queryForObject(sql, rowMapper, empno);
	}

}