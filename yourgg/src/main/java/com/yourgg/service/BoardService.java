package com.yourgg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yourgg.domain.BoardDTO;
import com.yourgg.persistence.BoardDAO;

@Service
public class BoardService {

    private final BoardDAO boardDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    public BoardService(BoardDAO boardDAO, BCryptPasswordEncoder passwordEncoder) {
        this.boardDAO = boardDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public String getPassword(int boardNumber) {
        return boardDAO.getPassword(boardNumber);
    }

    public int getTotalPost() {
        return boardDAO.getTotalPost();
    }

    public List<BoardDTO> getPosts(int nowPage) {
        return boardDAO.getPosts(nowPage);
    }

    public void registPost(BoardDTO boardDTO) {
        boardDTO.setBoardPassword(passwordEncoder.encode(boardDTO.getBoardPassword()));
        boardDAO.registPost(boardDTO);
    }

    public BoardDTO getPost(int boardNumber) {
        return boardDAO.getPost(boardNumber);
    }

    public boolean deletePost(BoardDTO boardDTO) {
        if (passwordEncoder.matches(boardDTO.getBoardPassword(), boardDAO.getPassword(boardDTO.getBoardNumber()))) {
            boardDAO.deletePost(boardDTO.getBoardNumber());
            return true;
        } else return false;
    }

    public boolean updateJudge(BoardDTO boardDTO) {
        if (passwordEncoder.matches(boardDTO.getBoardPassword(), boardDAO.getPassword(boardDTO.getBoardNumber()))) return true;
        else return false;
    }

    public void updatePost(BoardDTO boardDTO) {
        boardDAO.updatePost(boardDTO);
    }
}
