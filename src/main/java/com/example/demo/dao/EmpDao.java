package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Emp;

public interface EmpDao {
	// �����ڷ��� int: �۾������ ������� row�� ������ �ǹ��մϴ�.
	public int insert(Emp emp);
	public int update(Emp emp);
	public int delete(int empno);
	
	public List<Emp> findAll();
	public int count();
	public Emp findOne(int empno);
}