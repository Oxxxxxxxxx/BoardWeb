package com.yedam.web;
//게시글관련 보드 등록. 팀원1

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddBoardControl;
import com.yedam.control.BModFormControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardFormContorl;
import com.yedam.control.BoardListControl;
import com.yedam.control.BoardModControl;
import com.yedam.control.RemoveControl;

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
		menu.put("/modiForm", new BModFormControl());
		menu.put("/modifyBoard.do", new BoardModControl());
		menu.put("/removeBoard.do", new RemoveControl());
		
		//등록(화면, 기능)
		menu.put("/addBoardForm.do", new BoardFormContorl());
		menu.put("/addBoard.do", new AddBoardControl());
		return menu;
		
		
	}
	
		
}

