package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	//댓글 목록
	List<ReplyVO> selectList(int bno);
	List<ReplyVO> selectListPagin(SearchDTO search); // 페이지당 건수 출력
	// 삭제
	int deleteReply(int rno);
	
	//여러개 삭제
	int deleteReplys(String[] array);
	
	//추가
	int addReply(ReplyVO reply);
	int selectKey();
	
	//댓글건수
	int selectReplyCount(int bno);
}
