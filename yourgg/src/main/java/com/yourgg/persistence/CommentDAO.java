package com.yourgg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.yourgg.domain.CommentDTO;
import org.springframework.stereotype.Repository;

public interface CommentDAO {
	
	@Insert ("INSERT INTO BOARD_COMMENT VALUES (#{boardNumber}, comment_seq.nextval, #{commentWriter}, #{commentContent}, SYSDATE)")
	public void postComment(CommentDTO commentDTO);
	
	public List<CommentDTO> getCommentList(int boardNumber);

	public int getCommentInfo(int boardNumber);
}
