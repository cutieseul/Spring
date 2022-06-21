package com.springlec.base0602.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.springlec.base0602.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//list 출력
	public ArrayList<BDto> list(){
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent, bDate from mvc_board";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int bId =resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate"); //지금은 따로따로 찍힘 
				
				//하나 작은박스로  집어넣을려고 dto 만든것 
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate);
				
				//이제 큰박스에 하나하나 넣어야 함 dtos 에 하나씩 쌓임 
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   //finally는 이상이 없었을때나 있었을때나  메모리 정리를 해주는 것 
			
		try {
			if(resultSet != null) resultSet.close(); //데이터가 있으면 클로즈해라 
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return dtos;
	}//여기까지 list 
	
	
	
	
	
	public void write(String bName, String bTitle, String bContent) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board( bName, bTitle, bContent, bDate ) values (?,?,?,now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
		
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   //finally는 이상이 없었을때나 있었을때나  메모리 정리를 해주는 것 
			
		try {
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
}//여기까지 write
	
	public BDto contentView (String sbId) {
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; //검색에만 result가 있음
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(sbId));
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int bId =resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				//하나 작은박스로  집어넣을려고 dto 만든것 
				dto = new BDto(bId, bName, bTitle, bContent, bDate);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {   //finally는 이상이 없었을때나 있었을때나  메모리 정리를 해주는 것 
			
		try {
			if(resultSet != null) resultSet.close(); //데이터가 있으면 클로즈해라 
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return dto;
			
}//여기까지 하나씩 보여주기
	
	public void modify(String bId,String bName, String bTitle, String bContent) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4,Integer.parseInt(bId));
		
		
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   //finally는 이상이 없었을때나 있었을때나  메모리 정리를 해주는 것 
			
		try {
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
}//수정하기
	
public void delete(String bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "delete from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,Integer.parseInt(bId));
		
		
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   //finally는 이상이 없었을때나 있었을때나  메모리 정리를 해주는 것 
			
		try {
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
}//수정하기
	
	
	
}