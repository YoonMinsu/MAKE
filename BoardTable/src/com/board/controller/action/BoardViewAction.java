package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/board/boardView.jsp";
		String num = request.getParameter( "num" );
		
		BoardDAO.getInstance().updateReadCount( num );
		
		BoardDTO dto = BoardDAO.getInstance().selectOneBoardByNum( num );
		
		request.setAttribute( "board", dto );
		
		request.getRequestDispatcher( url ).forward( request, response );
	}

}
