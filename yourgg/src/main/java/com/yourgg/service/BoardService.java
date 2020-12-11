package com.yourgg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yourgg.domain.BoardVO;
import com.yourgg.persistence.BoardDAO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public String getPassword(int boardNumber) {
		return boardDAO.getPassword(boardNumber);
	}
	
	public int getTotalPost() {
		return boardDAO.getTotalPost();
	}
	
	public List<BoardVO> getPosts(int nowPage) {
		return boardDAO.getPosts(nowPage);
	}
	
	public void registPost(BoardVO boardVO) {
		boardVO.setBoardPassword(passwordEncoder.encode(boardVO.getBoardPassword()));
		boardDAO.registPost(boardVO);
	}
	
	public BoardVO getPost(int boardNumber) {
		return boardDAO.getPost(boardNumber);
	}
	
	public boolean deletePost(BoardVO boardVO) {
		if(passwordEncoder.matches(boardVO.getBoardPassword(), boardDAO.getPassword(boardVO.getBoardNumber()))) {
			boardDAO.deletePost(boardVO.getBoardNumber());
			return true;
		}
		else return false;
	}
	
	public boolean updateJudge(BoardVO boardVO) {
		if(passwordEncoder.matches(boardVO.getBoardPassword(), boardDAO.getPassword(boardVO.getBoardNumber()))) return true;
		else return false;
	}
	
	public void updatePost(BoardVO boardVO) {
		boardDAO.updatePost(boardVO);
	}
}
