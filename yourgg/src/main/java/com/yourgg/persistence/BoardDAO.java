package com.yourgg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yourgg.domain.BoardDTO;
import org.springframework.stereotype.Repository;

public interface BoardDAO {
	
	@Select ("SELECT COUNT(*) FROM BOARD")
	public int getTotalPost();
	
	public List<BoardDTO> getPosts(int nowPage);
	
	public void registPost(BoardDTO boardDTO);
	
	@Select ("SELECT BOARD_NUMBER, BOARD_REGDATE, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public BoardDTO getPost(int boardNumber);
	
	@Select ("SELECT BOARD_PASSWORD FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public String getPassword(int boardNumber);
	
	@Delete ("DELETE FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public void deletePost(int boardNumber);
	
	@Update ("UPDATE BOARD SET BOARD_TITLE = #{boardTitle}, BOARD_CONTENT = #{boardContent} WHERE BOARD_NUMBER = #{boardNumber}")
	public void updatePost(BoardDTO boardDTO);

}
