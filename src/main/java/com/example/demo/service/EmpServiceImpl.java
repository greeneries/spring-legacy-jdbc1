package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.dao.EmpDao;
import com.example.demo.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Override
	public int insert(Emp emp) throws Exception {
		
// transaction definition�� 4���� �Ӽ��� ��� ������ ���ΰ�?
	
/* Ʈ����� �ݸ� ���� (Isolation Level) ��ó: http://idkbj.tistory.com/31
 * ISOLATION_DEFAULT : �������� PlatformTransactionManager�� ���� ����Ʈ �ݸ� ����
 * ISOLATION_READ_UNCOMMITTED : �ݸ� ���� �� ���� ���� �ݸ� �����̴�. 
 *                              �� �ݸ� ������ �ٸ� Commit ���� ���� Ʈ����ǿ� ���� ����� �����͸� �� �� �ֱ� ������ ���� Ʈ������� ����� �������� �ʴ´�.
 * ISOLATION_READ_COMMITTED : �� ���� �����ͺ��̽������� ����Ʈ�� �����ϴ� �ݸ� �����̴�. 
 *                            �� �ݸ� ������ �ٸ� Ʈ����ǿ� ���� Commit ���� ���� �����ʹ� �ٸ� Ʈ����ǿ��� �� �� ������ �Ѵ�. 
 *                            �׷��� �����ڵ��� �ٸ� Ʈ����ǿ� ���� �Էµǰų� ������ �����ʹ� ��ȸ�� �� �ִ�.
 * ISOLATION_REPEATABLE_READ : ISOLATION_READ_COMMITTED ���ٴ� �ټ� ������ �ݸ� �����̴�. 
 *                             �� �ݸ� ������ �ٸ� Ʈ������� ���ο� �����͸� �Է��ߴٸ� ���Ӱ� �Էµ� �����͸� ��ȸ�� �� �ִٴ� ���� �ǹ��Ѵ�.
 * ISOLATION_SERIALIZABLE : ���� ���� ����� ������ �ŷ��� ���� �ݸ� ������ �����ϴ� ���� �����ϴ�. 
 *                          �� �ݸ� ������ �ϳ��� Ʈ������� �Ϸ�� �Ŀ� �ٸ� Ʈ������� �����ϴ� ��ó�� �����Ѵ�.          
 */
	

/* ���� ���� (Propagation Behavior) ��ó: http://idkbj.tistory.com/31
 * PROPAGATION_REQUIRED	: �̹� �ϳ��� Ʈ������� �����Ѵٸ� �� Ʈ������� �����ϰ�, Ʈ������� ���ٸ� ���ο� Ʈ������� �����Ѵ�.
 * PROPAGATION_SUPPORTS : �̹� Ʈ������� �����Ѵٸ� �� Ʈ������� �����ϰ�, Ʈ������� ���ٸ� ��-Ʈ����� ���·� �����Ѵ�.
 * PROPAGATION_MANDATORY : �̹� Ʈ������� �����Ѵٸ� �� Ʈ������� �����ϰ�, Ȱ��ȭ�� Ʈ������� ���ٸ� ���ܸ� ������.
 * PROPAGATION_REQUIRES_NEW : ������ ���ο� Ʈ������� �����Ѵ�. �̹� Ȱ��ȭ�� Ʈ������� �ִٸ� �Ͻ� �����Ѵ�.
 * PROPAGATION_NOT_SUPPORTED : Ȱ��ȭ�� Ʈ������� ���� ������ �������� �ʴ´�. ������ ��-Ʈ��������� �����ϰ� �����ϴ� Ʈ������� �Ͻ� �����Ѵ�.
 * PROPAGATION_NEVER : Ȱ��ȭ�� Ʈ������� �����ϴ��� ��-Ʈ����������� �����Ѵ�. Ȱ��ȭ�� Ʈ������� �����Ѵٸ� ���ܸ� ������.
 * PROPAGATION_NESTED : Ȱ��ȭ�� Ʈ������� �����Ѵٸ� ������ Ʈ��������� ����ȴ�. �۾� ������ TransactionDefinition.PROPAGATION_REQUIRED ���� ���õ� ��ó�� ����ȴ�.
 */
		
		
		
/*
 * �̹� �ϳ��� Ʈ������� �����Ѵٸ� �� Ʈ������� �����ϰ�, Ʈ������� ���ٸ� ���ο� Ʈ������� �����Ѵ�.
 */
		
		DefaultTransactionDefinition transactionDefinition = new 
    DefaultTransactionDefinition();
		// �Ʒ� ������ ��� ����Ʈ ���� ����ϰ� �ִ�.
		transactionDefinition.setPropagationBehavior(
    TransactionDefinition.PROPAGATION_REQUIRED); // 
		transactionDefinition.setIsolationLevel(
    TransactionDefinition.ISOLATION_DEFAULT); // database default ��
		transactionDefinition.setTimeout(-1); // -1�� �ð������� ���� �ʰڴٴ� �ǹ� 
		transactionDefinition.setReadOnly(false); // ��ȸ���� ������ �� true�� �����ϸ� �� transaction�� ��ġ�� �ٸ� transaction�� ���� �۾��ϴ� ���� ���

		TransactionStatus transactionStatus = transactionManager
    .getTransaction(transactionDefinition);
		
		int affected = 0;
		
		try {
			// ��� �κ� �ڵ� : Around:Before Advice
			
			// ************************************
			// Delegation: Ÿ�� �޼ҵ��� �ٽɷ����� ȣ���Ѵ�.
			affected = empDao.insert(emp);
			// ************************************
			
			// �ϴ� �κ� �ڵ� : Around:After Advice
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			throw e;
		}

		return affected;
	}

	@Override
	public int update(Emp emp) throws Exception {
		return empDao.update(emp);
	}

	@Override
	public int delete(int empno) throws Exception {
		return empDao.delete(empno);
	}

	@Override
	public List<Emp> findAll() throws Exception {
		return empDao.findAll();
	}

	@Override
	public int count() throws Exception {
		return empDao.count();
	}

	@Override
	public Emp findOne(int empno) throws Exception {
		return empDao.findOne(empno);
	}

}