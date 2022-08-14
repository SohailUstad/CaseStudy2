package SOHAIL_CoreJAVA_Rating_WithDatabase.Entities;



public class Student {
	private String subjectName;
	private String studentName;
	private String category;
	private String dateOfSubmission;
	private int points;
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Student(String subjectName, String studentName, String category, String dateOfSubmission, int points) {
		super();
		this.subjectName = subjectName;
		this.studentName = studentName;
		this.category = category;
		this.dateOfSubmission = dateOfSubmission;
		this.points = points;
	}
	public Student(String studentName, String category, String dateOfSubmission, int points) {
		super();
		this.studentName = studentName;
		this.category = category;
		this.dateOfSubmission = dateOfSubmission;
		this.points = points;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
