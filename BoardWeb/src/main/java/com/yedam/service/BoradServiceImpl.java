package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoradServiceImpl implements BoardService {
	
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	
	@Override
	public List<BoardVO> boardList(SearchDTO search) {
		// TODO Auto-generated method stub
	//	return mapper.selectListPaging(search);
		return mapper.selectList();
	}

	@Override
	public boolean addboard(BoardVO board) {
		// TODO Auto-generated method stub
		return mapper.insertBoard(board) == 1;
	}

	@Override
	public boolean modifyboard(BoardVO board) {
		// TODO Auto-generated method stub
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeboard(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.deleteBoard(boardNo) == 1;
	}

	@Override
	public BoardVO getboard(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.selectBoard(boardNo);
	}

	@Override
	public int getTotalCnt(SearchDTO search) {
		// TODO Auto-generated method stub
		return mapper.selectCount(search);
	}

	
}
