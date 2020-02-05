package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		BoardDTO dto = BoardDAO.getInstance().selectOneBoardByNum( num );
		
		String url = null;
		if( dto.getPass().equals( pass )) {
			url = "/board/checkSuccess.jsp";
		} else {
			url = "/board/boardCheckPass.jsp";
			request.setAttribute( "message", "비밀번호가 틀렸습니다.!" );
		}
		
		request.getRequestDispatcher( url ).forward( request, response );
	}

}
