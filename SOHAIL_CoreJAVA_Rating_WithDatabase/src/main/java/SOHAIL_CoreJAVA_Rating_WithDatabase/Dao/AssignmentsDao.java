package SOHAIL_CoreJAVA_Rating_WithDatabase.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Assignments;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Helper.ConnectionProvider;

public class AssignmentsDao {
	public List<Assignments> getAllAssignments() {
		List<Assignments> assignments = new ArrayList<Assignments>();

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select * from Assignments";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Assignments a = new Assignments();
				a.setSerialNo(rs.getInt("serialNo"));
				a.setStudentName(rs.getString("studentName"));
				a.setSubject(rs.getString("subject"));
				a.setAssignmentCategory(rs.getString("assignmentCategory"));
				a.setDateOfSubmission(rs.getString("dateOfSubmission"));
				a.setPoints(rs.getInt("points"));
				assignments.add(a);
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignments;
	}

	public boolean addAssignments(Assignments a) {
		boolean b = false;

		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "insert into Assignments(serialNo,studentName,subject,assignmentCategory,dateOfSubmission,points) values(?,?,?,?,?,?)";
			PreparedStatement p=con.prepareStatement(query);
			p.setInt(1, a.getSerialNo());
			p.setString(2, a.getStudentName());
			p.setString(3, a.getSubject());
			p.setString(4, a.getAssignmentCategory());
			p.setString(5, a.getDateOfSubmission());
			p.setInt(6, a.getPoints());
			
			p.executeUpdate();
			b=true;
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}
	
	public boolean removeAssignment(int serialNumber) {
		boolean b=false;
		
		if(!getSerialNumber().contains(serialNumber)) {
			return b;
		}else {
			try {
				Connection con = new ConnectionProvider().getConnection();
				String query = "delete from Assignments where serialNo=?";
				PreparedStatement p=con.prepareStatement(query);
				p.setInt(1, serialNumber);
				p.execute();
				b=true;
				
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return b;
		}
	}
	
	public List<Integer> getSerialNumber(){
		List<Integer> list=new ArrayList<Integer>();
		
		try {
			Connection con = new ConnectionProvider().getConnection();

			String query = "select serialNo from Assignments";
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getInt("serialNo"));
			}
			
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return list;
	}

}
