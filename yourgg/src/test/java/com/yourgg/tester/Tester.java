package com.yourgg.tester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yourgg.domain.CommentDTO;
import com.yourgg.service.CommentService;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class Tester {

	@Autowired
	private CommentService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

//	@Test
//	public void test() {
//		
//		int nowPage = 51;
//		int totalPost = service.getTotalPost();
//		int totalPage = (int)Math.ceil(service.getTotalPost()/20);
//		int endPage = (int)(Math.ceil(nowPage/10.0))*10; 
//		int startPage = endPage - 9;
//		
//		log.info("현재 페이지 = " + nowPage);
//		log.info("총 게시글 = " + totalPost);
//		log.info("총 페이지 = " + totalPage);
//		log.info("시작 페이지 = " + startPage);
//		log.info("끝 페이지 = " + endPage); 
//		
//		service.getPosts(new PageVO(nowPage, 20));
//
//	}
	
	@Test
	public void test() {
		int boardNumber = 81;
		log.info(boardNumber);
		
		CommentDTO comment = new CommentDTO();
		log.info(comment.getBoardNumber());
		log.info(comment.getCommentNumber());
		log.info(comment.getCommentWriter());
		log.info(comment.getCommentContent());
		log.info(comment.getCommentRegdate());
		
		service.getCommentList(boardNumber);
		
		log.info(comment.getBoardNumber());
		log.info(comment.getCommentNumber());
		log.info(comment.getCommentWriter());
		log.info(comment.getCommentContent());
		log.info(comment.getCommentRegdate());
	
	}

}
