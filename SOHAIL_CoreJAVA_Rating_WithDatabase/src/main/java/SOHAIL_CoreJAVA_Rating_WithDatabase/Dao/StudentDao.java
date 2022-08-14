package SOHAIL_CoreJAVA_Rating_WithDatabase.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Student;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Subject;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Helper.ConnectionProvider;

public class StudentDao {
	
	public List<Student> getAllAssignmentsList(String student) {
		List<Student> list=new ArrayList<Student>();

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select * from " + student;
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student ss=new Student();
				ss.setCategory(rs.getString("category"));
				ss.setDateOfSubmission(rs.getString("dateOfSubmission"));
				ss.setPoints(rs.getInt("points"));
				ss.setSubjectName(rs.getString("subjectName"));
				list.add(ss);
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
	public boolean assing(Subject s) {
		boolean b = false;
		try {
			Connection con = new ConnectionProvider().getConnection();

			String q1 = "CREATE TABLE IF NOT EXISTS " + s.getStudentName() + "(" + "subjectName varchar(255),"
					+ "category varchar(255)," + "dateOfSubmission varchar(255) default 'dos',"
					+ "points int(20) default(0))";
			String q2 = "CREATE TABLE IF NOT EXISTS " + s.getSubjectName() + "(" + "studentName varchar(255),"
					+ "category varchar(255))";
			String q3 = "insert into " + s.getStudentName() + "(subjectName , category) values(?,?)";
			String q4 = "insert into " + s.getSubjectName() + "(studentName , category) values(?,?)";

			PreparedStatement ps1 = con.prepareStatement(q1);
			PreparedStatement ps2 = con.prepareStatement(q2);
			PreparedStatement ps3 = con.prepareStatement(q3);
			PreparedStatement ps4 = con.prepareStatement(q4);
			ps3.setString(1, s.getSubjectName());
			ps3.setString(2, s.getCategory());

			ps4.setString(1, s.getStudentName());
			ps4.setString(2, s.getCategory());

			ps1.execute();
			ps2.execute();
//			if (getStudentsAndCategories(s.getSubjectName(), s.getStudentName(), s.getCategory()) == null
//					&& getSubjectsAndCategories(s.getStudentName(), s.getSubjectName(), s.getCategory()) != null) {
//				ps3.executeUpdate();
//				ps4.executeUpdate();
//				b = true;
//			} else {
//				b = false;
//			}
			ps3.executeUpdate();
			ps4.executeUpdate();
			b=true;
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public String getSubjectsAndCategories(String student, String sub, String cat) {
		String list = null;

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select * from " + student + " where subjectName=? and category=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, sub);
			ps.setString(2, cat);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				list = rs.getString("subjectName");
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	public String getStudentsAndCategories(String subject, String stu, String cat) {
		String list = null;

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select * from " + subject + " where studentName=? and category=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, stu);
			ps.setString(2, cat);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				list = rs.getString("studentName");
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
	
	public boolean deleteAssignment(Subject s) {
		boolean b=false;
		
		try {
			Connection con = new ConnectionProvider().getConnection();
			String query = "delete from "+ s.getStudentName() +" where subjectName=? and category=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s.getSubjectName());
			ps.setString(2, s.getCategory());
			ps.executeUpdate();
			b=true;
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return b;
	}
	
	public boolean updateAssignment(Subject s) {
		boolean b=false;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="update "+s.getStudentName()+" set dateOfSubmission=? , points=?  where subjectName=? and category=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, s.getDateOfSubmission());
			ps.setInt(2, s.getPoints());
			ps.setString(3, s.getSubjectName());
			ps.setString(4, s.getCategory());
			
			ps.executeUpdate();
			b=true;
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return b;
	}
	

	
}
