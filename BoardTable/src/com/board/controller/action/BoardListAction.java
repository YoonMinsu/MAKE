package com.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "board/boardList.jsp";
		
		BoardDAO dao = BoardDAO.getInstance();
		
		ArrayList<BoardDTO> list = dao.selectAllBoards();
		
		request.setAttribute( "boardlist", list );
		
		RequestDispatcher dispatcher = request.getRequestDispatcher( url );
		dispatcher.forward( request, response );
	}

}
