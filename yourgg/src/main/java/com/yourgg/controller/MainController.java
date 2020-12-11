package com.yourgg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourgg.domain.BoardVO;
import com.yourgg.domain.CommentVO;
import com.yourgg.domain.PageDTO;
import com.yourgg.service.BoardService;
import com.yourgg.service.CommentService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MainController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("board", boardService.getPosts(1));
		model.addAttribute("pageInfo", new PageDTO(1, boardService.getTotalPost()));
		return "index";
	}

	@GetMapping("/boardList")
	public String getBoard(Model model, @RequestParam("page") int nowPage) {
		model.addAttribute("board", boardService.getPosts(nowPage));
		model.addAttribute("pageInfo", new PageDTO(nowPage, boardService.getTotalPost()));
		return "index";
	}

	@GetMapping("/post")
	public String doPost(Model model, @RequestParam("page") int nowPage) {
		model.addAttribute("pageInfo", new PageDTO(nowPage, boardService.getTotalPost()));
		return "post";
	}

	@PostMapping("/post/regist")
	public String doRegist(BoardVO boardVO) {
		boardService.registPost(boardVO);
		return "redirect:/";
	}

	@GetMapping("/get/view-post")
	public String seePost(Model model, @RequestParam("page") int nowPage, @RequestParam("boardNumber") int boardNumber) {
		model.addAttribute("pageInfo", new PageDTO(nowPage, boardService.getTotalPost()));
		model.addAttribute("board", boardService.getPost(boardNumber));
		model.addAttribute("comment", commentService.getCommentList(boardNumber));
		model.addAttribute("count", commentService.getCommentInfo(boardNumber));
		return "view-post";
	}

	@PostMapping("/delete")
	@ResponseBody
	public boolean deletePost(BoardVO boardVO) {
		return boardService.deletePost(boardVO);
	}

	@PostMapping("/update-judge")
	@ResponseBody
	public boolean updatePost(BoardVO boardVO) {
		return boardService.updateJudge(boardVO);
	}

	@PostMapping("/post/update")
	public String doUpdatePost(BoardVO boardVO) {
		boardService.updatePost(boardVO);
		return "redirect:/";
	}

	@PostMapping("/post/comment")
	@ResponseBody
	public Map<String, Object> postMethod(@RequestParam("page") int nowPage, CommentVO commentVO) {
		
		log.info("nowPage = " + nowPage);
		log.info("commentVO = " + commentVO);
		
		commentService.postComment(commentVO);
		Map<String, Object> map = new HashMap<>();
		map.put("page", nowPage);
		map.put("comment", commentService.getCommentList(commentVO.getBoardNumber()));
		map.put("count", commentService.getCommentInfo(commentVO.getBoardNumber()));
		map.put("pageInfo", new PageDTO(nowPage, boardService.getTotalPost()));
		map.put("board", boardService.getPost(commentVO.getBoardNumber()));
		return map;
	}

}
