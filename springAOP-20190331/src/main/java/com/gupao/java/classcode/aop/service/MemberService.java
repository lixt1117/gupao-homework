package com.gupao.java.classcode.aop.service;

import com.gupao.java.classcode.aop.aspect.AnnotaionAspect;
import com.gupao.java.classcode.model.Member;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 注解版业务操作类
 * @author Tom
 */
@Service
public class MemberService {

	private final static Logger log = Logger.getLogger(AnnotaionAspect.class);
	
	public Member get(long id){
		log.info("getMemberById method . . .");
		return new Member();
	}
	
	
	public Member get(){
		log.info("getMember method . . .");
		return new Member();
	}
	
	public void save(Member member){
		log.info("save member method . . .");
	}
	
	public boolean delete(long id) throws Exception{
		log.info("delete method . . .");
		throw new Exception("spring aop ThrowAdvice演示");
	}
	
}
