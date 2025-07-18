package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;

public interface BoardMapper {

	// [1] 게시글 목록 조회
	List<BoardVO> getList();

	// [2] 게시글 쓰기
	void insert(BoardVO boardVO);
	
	// [2-2] 게시글 쓰기 + 글 번호 반환 기능 (부여)
	int insertSelectKey(BoardVO boardVO);
	
	// [3] 게시글 상세보기(조회)
	BoardVO read(long bno);
	
	// [4] 게시글 수정하기
	int update(BoardVO boardVO);
	
	// [5] 게시글 삭제하기
	int delete(Long bno);
	
}
