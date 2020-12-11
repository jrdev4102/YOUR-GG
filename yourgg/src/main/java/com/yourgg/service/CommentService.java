package com.yourgg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourgg.domain.BoardVO;
import com.yourgg.domain.CommentVO;
import com.yourgg.persistence.CommentDAO;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;
	
	public void postComment(CommentVO commentVO) {
		commentDAO.postComment(commentVO);
	}
	
	public List<CommentVO> getCommentList(int boardNumber) {
		return commentDAO.getCommentList(boardNumber);
	}
	
	public int getCommentInfo(int boardNumber) {
		return commentDAO.getCommentInfo(boardNumber);
	}

}
