package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;

public interface BoardService {

	// 게시글 목록
	List<BoardVO> getList();
	
	// 게시글 작성
	int register(BoardVO boardVO);
	
	// 게시글 조회
	BoardVO get(Long bno);

	// 게시글 수정
	boolean modify(BoardVO boardVO);
	
	// 게시글 삭제
	boolean remove(Long bno);
	
}
