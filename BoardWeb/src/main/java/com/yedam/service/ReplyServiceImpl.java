package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService{

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	
	@Override
	public List<ReplyVO> replyList(SearchDTO search) {
		return mapper.selectList(search.getBoardNo()); 
		//return mapper.selectListPagin(search);
	}

	@Override
	public boolean removeReply(int rno) {
		return mapper.deleteReply(rno) == 1;
	}

	@Override
	public boolean removeReplys(String[] array) {
		return mapper.deleteReplys(array) > 0;
	}

	@Override
	public boolean addReplys(ReplyVO reply) {
		int rno = mapper.selectKey(); // replyNo, reply, replyer, boardNo
		reply.setReplyNo(rno);
		return mapper.addReply(reply) == 1;
	}

	@Override
	public int getReplyCount(int bno) {
		// TODO Auto-generated method stub
		return mapper.selectReplyCount(bno);
	}

}
