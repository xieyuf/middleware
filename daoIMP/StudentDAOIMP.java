package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	    	String sql = "INSERT INTO student (id, name) values (?,?)";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //针对数据库的具体操作
	    try{
	        conn = new DataBaseConnection();
	        
	        pstmt = conn.getConnection().prepareStatement(sql);
	        pstmt.setLong(1,s.getID());
	        //pstmt.setString(1,s.getID());
	        pstmt.setString(2,s.getName());
	  
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        }
	     catch(Exception e){  }
	      }

	    public void update(Student s){
			String sql = "UPDATE student SET name = ? WHERE id = ?";
			PreparedStatement pstmt = null;
			try{
				DataBaseConnection conn = new DataBaseConnection();
				pstmt.getConnection().prepareStatement(sql);
				pstmt.setLong(1,s.getID());
				pstmt.setString(2,s.getName());
				pstmt.execute();
				pstmt.close();
				conn.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		public void delete(String iD){
			PreparedStatement pstmt;
			String sql = "DELETE FROM student WHERE id = ?";
			try {
				DataBaseConnection conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1, Long.parseLong(iD));
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public List<Student> findAll(){
			List<Student> students = new ArrayList<>();
			String sql = "SELECT * FROM student";
			PreparedStatement pstmt;
			try {
				DataBaseConnection conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				ResultSet resultSet = pstmt.executeQuery();
				while(resultSet.next()) {
					students.add(new Student(resultSet.getLong("id"), resultSet.getString("name")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return students;
		}

		public Student findByID(long iD){
			Student student = null;
			PreparedStatement pstmt;
			String sql = "SELECT * FROM student WHERE id = ?";
			try {
				DataBaseConnection conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setInt(1, (int) iD);
				ResultSet resultSet = pstmt.executeQuery();
				resultSet.next();
				student = new Student(resultSet.getLong("id"), resultSet.getString("name"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return student;
		}

}
