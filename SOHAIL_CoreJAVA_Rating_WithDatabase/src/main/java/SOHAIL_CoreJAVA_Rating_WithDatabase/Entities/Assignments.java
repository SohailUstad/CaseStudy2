package SOHAIL_CoreJAVA_Rating_WithDatabase.Entities;

public class Assignments {
	private int serialNo;
	private String studentName;
	private String subject;
	private String assignmentCategory;
	private String dateOfSubmission;
	private int points;
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAssignmentCategory() {
		return assignmentCategory;
	}
	public void setAssignmentCategory(String assignmentCategory) {
		this.assignmentCategory = assignmentCategory;
	}
	public String getDateOfSubmission() {
		return dateOfSubmission;
	}
	public void setDateOfSubmission(String dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Assignments(int serialNo, String studentName, String subject, String assignmentCategory,
			String dateOfSubmission, int points) {
		super();
		this.serialNo = serialNo;
		this.studentName = studentName;
		this.subject = subject;
		this.assignmentCategory = assignmentCategory;
		this.dateOfSubmission = dateOfSubmission;
		this.points = points;
	}
	public Assignments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Assignments(String studentName, String subject, String assignmentCategory, String dateOfSubmission,
			int points) {
		super();
		this.studentName = studentName;
		this.subject = subject;
		this.assignmentCategory = assignmentCategory;
		this.dateOfSubmission = dateOfSubmission;
		this.points = points;
	}
	
	
	
	
}
