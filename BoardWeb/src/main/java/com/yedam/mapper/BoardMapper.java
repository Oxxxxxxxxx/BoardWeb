package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

// 목록, 추가, 수정, 삭제, 단건
public interface BoardMapper {
	List<BoardVO> selectList(); //전체목록
	List<BoardVO> selectListPaging(SearchDTO search);
	int insertBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int deleteBoard(int boardNo);
	BoardVO selectBoard(int boardNO);
	
	//페이징 게산을 위한 전체껀수
	int selectCount(SearchDTO search);
	
	
}
