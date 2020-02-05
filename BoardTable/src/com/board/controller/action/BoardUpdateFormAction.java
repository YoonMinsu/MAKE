package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num = request.getParameter( "num" );
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.updateReadCount( num );
		
		BoardDTO dto = dao.selectOneBoardByNum( num );
		
		request.setAttribute( "board", dto );
		request.getRequestDispatcher( "/board/boardUpdate.jsp" ).forward( request, response );
	}

}
