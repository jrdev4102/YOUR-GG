package com.yourgg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yourgg.domain.BoardVO;

public interface BoardDAO {
	
	@Select ("SELECT COUNT(*) FROM BOARD")
	public int getTotalPost();
	
	public List<BoardVO> getPosts(int nowPage);
	
	public void registPost(BoardVO boardVO);
	
	@Select ("SELECT BOARD_NUMBER, BOARD_REGDATE, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public BoardVO getPost(int boardNumber);
	
	@Select ("SELECT BOARD_PASSWORD FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public String getPassword(int boardNumber);
	
	@Delete ("DELETE FROM BOARD WHERE BOARD_NUMBER = #{param}")
	public void deletePost(int boardNumber);
	
	@Update ("UPDATE BOARD SET BOARD_TITLE = #{boardTitle}, BOARD_CONTENT = #{boardContent} WHERE BOARD_NUMBER = #{boardNumber}")
	public void updatePost(BoardVO boardVO);

}
