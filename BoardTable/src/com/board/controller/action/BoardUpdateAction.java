package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		BoardDTO dto = new BoardDTO();
		dto.setNum( Integer.parseInt( request.getParameter( "num" ) ) );
		dto.setName( request.getParameter( "name" ) );
		dto.setPass( request.getParameter( "pass" ) );
		dto.setEmail( request.getParameter( "email" ) );
		dto.setTitle( request.getParameter( "title" ) );
		dto.setContent( request.getParameter( "content" ) );
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.updateBoard( dto );
		
		new BoardListAction().execute( request, response );
	}

}
