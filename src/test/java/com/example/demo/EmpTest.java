package com.example.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.EmpDao;
import com.example.demo.dao.EmpDaoImpl;
import com.example.demo.model.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
  "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class EmpTest {

	@Autowired
	private EmpDao dao;
	
	@Test
	public void testDi() {
		System.out.println(dao);
		System.out.println(((EmpDaoImpl)dao).jdbcTemplate);
	}

	@Test
	public void testInsert() {
		Emp emp = new Emp();
		emp.setEmpno(3201);
		emp.setEname("È«±æµ¿");
		emp.setJob("µµµÏ");
		emp.setSal(999);
		
		int affected = dao.insert(emp);
		System.out.println("affected = " + affected);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		List<Emp> emps = dao.findAll();
		System.out.println(emps.size());
		
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

}
