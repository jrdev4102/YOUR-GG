package com.yourgg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourgg.domain.CommentDTO;
import com.yourgg.persistence.CommentDAO;

@Service
public class CommentService {

    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void postComment(CommentDTO commentDTO) {
        commentDAO.postComment(commentDTO);
    }

    public List<CommentDTO> getCommentList(int boardNumber) {
        return commentDAO.getCommentList(boardNumber);
    }

    public int getCommentInfo(int boardNumber) {
        return commentDAO.getCommentInfo(boardNumber);
    }

}
