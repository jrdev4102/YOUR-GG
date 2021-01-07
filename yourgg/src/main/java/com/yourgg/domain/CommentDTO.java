package com.yourgg.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private int boardNumber;
	private int commentNumber;
	private String commentWriter;
	private String commentContent;
	private Date commentRegdate;
}
