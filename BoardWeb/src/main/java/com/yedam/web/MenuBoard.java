package com.yedam.web;
//게시글관련 보드 등록. 팀원1

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;

public class MenuBoard {
	private static MenuBoard instance = new MenuBoard();
	private MenuBoard() {}
	public static MenuBoard getInstance() {
		return instance;
	}
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
		menu.put("/boardList.do", new BoardListControl());
		menu.put("/getBoard.do", new BoardControl());
		return menu;
	}
	
		
}

