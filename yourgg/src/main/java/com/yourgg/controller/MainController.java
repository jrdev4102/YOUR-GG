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

import com.yourgg.domain.BoardDTO;
import com.yourgg.domain.CommentDTO;
import com.yourgg.domain.PageVO;
import com.yourgg.service.BoardService;
import com.yourgg.service.CommentService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MainController {

    private final BoardService boardService;
    private final CommentService commentService;

    public MainController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("board", boardService.getPosts(1));
        model.addAttribute("pageInfo", new PageVO(1, boardService.getTotalPost()));
        return "index";
    }

    @GetMapping("/boardList")
    public String getBoard(Model model, @RequestParam("page") int nowPage) {
        model.addAttribute("board", boardService.getPosts(nowPage));
        model.addAttribute("pageInfo", new PageVO(nowPage, boardService.getTotalPost()));
        return "index";
    }

    @GetMapping("/post")
    public String doPost(Model model, @RequestParam("page") int nowPage) {
        model.addAttribute("pageInfo", new PageVO(nowPage, boardService.getTotalPost()));
        return "post";
    }

    @PostMapping("/post/regist")
    public String doRegist(BoardDTO boardDTO) {
        boardService.registPost(boardDTO);
        return "redirect:/";
    }

    @GetMapping("/get/view-post")
    public String seePost(Model model, @RequestParam("page") int nowPage, @RequestParam("boardNumber") int boardNumber) {
        model.addAttribute("pageInfo", new PageVO(nowPage, boardService.getTotalPost()));
        model.addAttribute("board", boardService.getPost(boardNumber));
        model.addAttribute("comment", commentService.getCommentList(boardNumber));
        model.addAttribute("count", commentService.getCommentInfo(boardNumber));
        return "view-post";
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deletePost(BoardDTO boardDTO) {
        return boardService.deletePost(boardDTO);
    }

    @PostMapping("/update-judge")
    @ResponseBody
    public boolean updatePost(BoardDTO boardDTO) {
        return boardService.updateJudge(boardDTO);
    }

    @PostMapping("/post/update")
    public String doUpdatePost(BoardDTO boardDTO) {
        boardService.updatePost(boardDTO);
        return "redirect:/";
    }

    @PostMapping("/post/comment")
    @ResponseBody
    public Map<String, Object> postMethod(@RequestParam("page") int nowPage, CommentDTO commentDTO) {
        commentService.postComment(commentDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("page", nowPage);
        map.put("comment", commentService.getCommentList(commentDTO.getBoardNumber()));
        map.put("count", commentService.getCommentInfo(commentDTO.getBoardNumber()));
        map.put("pageInfo", new PageVO(nowPage, boardService.getTotalPost()));
        map.put("board", boardService.getPost(commentDTO.getBoardNumber()));
        return map;
    }

}
