package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.EmpDao;
import com.example.demo.model.Emp;

@Controller
public class EmpController {
	@Autowired
	private EmpDao empDao;

//	http://localhost:8080/demo/emps
	@RequestMapping(value="/emps", method=RequestMethod.GET)
	public String getAll(Model model) {
		// DAO���� �����͸� ���ش޶�� ��û�Ѵ�.
		List<Emp> emps = empDao.findAll();
		// Model ��ü�� �����͸� �߰��ϸ� 
		// �������� HttpServletRequest ��ü�� �ű��.
		// HttpServletRequest ��ü�� �����ϴ� �����ʹ� 
		// JSP���� �����Ͽ� ����� �� �ִ�.
		model.addAttribute("emps", emps);
		// �������� ������ JSP �� ���ϸ��� �����Ѵ�.
		return "emp-list";
	}
	
}