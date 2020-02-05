package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.board.dto.BoardDTO;
import com.board.util.DBConnManager;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private BoardDAO() { }
	
	public ArrayList<BoardDTO> selectAllBoards() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( "select * from board order by num desc" );
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				BoardDTO dto = new BoardDTO();
				dto.setNum( rs.getInt( "num" ) );
				dto.setPass( rs.getString( "pass" ) );
				dto.setName( rs.getString( "name" ) );
				dto.setEmail( rs.getString( "email" ) );
				dto.setTitle( rs.getString( "title" ) );
				dto.setContent( rs.getString( "content" ) );
				dto.setReadcount(  rs.getInt( "readcount" ) );
				dto.setWritedate( rs.getTimestamp( "writedate" ) );
				list.add( dto );
			} 
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt, rs );
		}
		return list;
	}
	
	public void insertBoard( BoardDTO dto ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnManager.getConnection();
			String sql = "insert into board( num, name, email, pass, title, content)"
					+ "values( board_seq.nextval, ?, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, dto.getName() );
			pstmt.setString( 2, dto.getEmail() );
			pstmt.setString( 3, dto.getPass() );
			pstmt.setString( 4, dto.getTitle() );
			pstmt.setString( 5, dto.getContent() );
			
			pstmt.executeUpdate();
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt );
		}
	}
	
	// 조회수 증가!
	public void updateReadCount( String num ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( "update board set readcount = readcount + 1 where num = ?" );
			pstmt.setString( 1, num );
			pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt );
		}
	}
	
	// 게시판 글 상세 내용 보기 : 글번호로 찾아옴
	public BoardDTO selectOneBoardByNum( String num ) {
		BoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( "select * from board where num = ?" );
			pstmt.setString( 1, num );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				dto = new BoardDTO();
				dto.setNum( rs.getInt( "num" ) );
				dto.setPass( rs.getString( "pass" ) );
				dto.setName( rs.getString( "name" ) );
				dto.setEmail( rs.getString( "email" ) );
				dto.setTitle( rs.getString( "title" ) );
				dto.setContent( rs.getString( "content" ) );
				dto.setReadcount( rs.getInt( "readcount" ) );
				dto.setWritedate( rs.getTimestamp( "writedate" ) );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt, rs );
		}
		return dto;
	}
	
	public void updateBoard( BoardDTO dto ) {
		String sql = "update board set name = ?, email = ?, pass = ?, title = ?, content = ? where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, dto.getName() );
			pstmt.setString( 2, dto.getEmail() );
			pstmt.setString( 3, dto.getPass() );
			pstmt.setString( 4, dto.getTitle() );
			pstmt.setString( 5, dto.getContent() );
			pstmt.setInt( 6, dto.getNum() );
			
			pstmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt );
		}
	}
	
	public BoardDTO checkPassWord( String pass, String num ) {
		String sql = "select * from board where pass = ? and num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, pass );
			pstmt.setString( 2, num );
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				dto = new BoardDTO();
				dto.setNum( rs.getInt( "num" ) );
				dto.setName( rs.getString( "name" ) );
				dto.setEmail( rs.getString( "email") );
				dto.setPass( rs.getString( "pass" ));
				dto.setTitle( rs.getString( "title" ) );
				dto.setContent( rs.getString( "content" ) );
				dto.setReadcount( rs.getInt( "readcount" ) );
				dto.setWritedate( rs.getTimestamp( "writedate" ) );
			}
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			DBConnManager.close( conn, pstmt, rs );
		}
		return dto;
	}
	
	public void deleteBoard( String num ) {
		String sql = "delete from board where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnManager.getConnection();
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, num );
			pstmt.executeUpdate();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}
}	