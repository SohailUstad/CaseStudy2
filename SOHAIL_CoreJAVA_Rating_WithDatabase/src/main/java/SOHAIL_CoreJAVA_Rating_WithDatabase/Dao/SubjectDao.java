package SOHAIL_CoreJAVA_Rating_WithDatabase.Dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Student;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Subject;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Helper.ConnectionProvider;


public class SubjectDao {
	public List<Subject> getAllStudents(String subject) {
		List<Subject> list=new ArrayList<Subject>();

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select * from " + subject;
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Subject ss=new Subject();
				ss.setCategory(rs.getString("category"));
				ss.setStudentName(rs.getString("studentName"));
				list.add(ss);
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
	
	
	public boolean enroll(Student s) {
		boolean b=false;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			
			String q1 = "CREATE TABLE IF NOT EXISTS " + s.getStudentName() + "(" + "subjectName varchar(255),"
					+ "category varchar(255)," + "dateOfSubmission varchar(255) default 'dos', "+ "points int(20) default(0))";
			String q2 = "CREATE TABLE IF NOT EXISTS " + s.getSubjectName() + "(" + "studentName varchar(255),"
					+ "category varchar(255))";
			String q3="insert into "+s.getSubjectName()+"(studentName , category) values(?,?)";
			PreparedStatement ps1 = con.prepareStatement(q1);
			PreparedStatement ps2 = con.prepareStatement(q2);
			PreparedStatement ps3 = con.prepareStatement(q3);
			ps3.setString(1, s.getStudentName());
			ps3.setString(2, s.getCategory());
			
			ps1.execute();
			ps2.execute();
			StudentDao stdao=new StudentDao();
			String s1=stdao.getStudentsAndCategories(s.getSubjectName(), s.getStudentName(), s.getCategory());
			if(s1==null) {
				ps3.executeUpdate();
				b=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return b;
	}
	
	public String getStudentsAndCategories(String subject,String stu,String cat){
		String list=null;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String query="select * from "+subject+" where studentName=? and category=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, stu);
			ps.setString(2, cat);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				list=rs.getString("studentName");
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean deleteStudent(Student s) {
		boolean b=false;
		
		try {
			Connection con = new ConnectionProvider().getConnection();
			String query = "delete from "+ s.getSubjectName() +" where studentName=? and category=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s.getStudentName());
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
	
	public boolean updateStudent(Student s) {
		boolean b=false;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="update "+s.getSubjectName()+" set studentName=?, category=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, s.getStudentName());
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
	
}
