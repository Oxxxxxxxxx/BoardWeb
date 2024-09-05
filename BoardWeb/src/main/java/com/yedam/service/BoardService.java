package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardService {
	List<BoardVO> boardList(SearchDTO search); // 목록
	boolean addboard(BoardVO board); //추가
	boolean modifyboard(BoardVO board); //수정
	boolean removeboard(int boardNo); //삭제.
	BoardVO getboard(int boardNo); //단건조회
	
	int getTotalCnt(SearchDTO count);
}
