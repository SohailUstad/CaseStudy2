package SOHAIL_CoreJAVA_Rating_WithDatabase.Dao;

import java.sql.Connection;
import java.sql.Statement;

import SOHAIL_CoreJAVA_Rating_WithDatabase.Helper.ConnectionProvider;

public class GeneralDao {
	public boolean createTables() {
		boolean b=false;
		
		try {
			Connection con= new ConnectionProvider().getConnection();
			
			String query1="CREATE TABLE IF NOT EXISTS Distributions("
					+ "assignmentCategory varchar(255),"
					+ "weight int(11))";
			String query2="CREATE TABLE IF NOT EXISTS Assignments("
					+ "serialNo int(20),"
					+ "studentName varchar(255),"
					+ "subject varchar(255),"
					+ "assignmentCategory varchar(255),"
					+ "dateOfSubmission varchar(255),"
					+ "points int(20))";
			String query3="CREATE TABLE IF NOT EXISTS Students("
					+ "studentId int(20) not null AUTO_INCREMENT,"
					+ "studentName varchar(255),"
					+ "primary key (studentId))";
			String query4="CREATE TABLE IF NOT EXISTS Subjects("
					+ "subjectId int(20) not null AUTO_INCREMENT,"
					+ "subjectName varchar(255),"
					+ "primary key (subjectId))";
			Statement s=con.createStatement();
			s.execute(query1);
			s.execute(query2);
			s.execute(query3);
			s.execute(query4);
			b=true;
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
}	
