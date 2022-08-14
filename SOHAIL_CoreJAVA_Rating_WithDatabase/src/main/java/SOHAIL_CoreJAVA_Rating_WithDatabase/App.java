package SOHAIL_CoreJAVA_Rating_WithDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import SOHAIL_CoreJAVA_Rating_WithDatabase.Dao.AssignmentsDao;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Dao.DistributionsDao;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Dao.StudentDao;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Dao.SubjectDao;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Assignments;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Distributions;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Student;
import SOHAIL_CoreJAVA_Rating_WithDatabase.Entities.Subject;

public class App {

	public double getOverAllRating(String student, String subject, List<Assignments> assignment) {
		double rating = 0;

		rating = getScore(student, subject, "test", assignment) + getScore(student, subject, "quiz", assignment)
				+ getScore(student, subject, "lab", assignment) + getScore(student, subject, "project", assignment);

		return rating;
	}

	public double getScore(String student, String subject, String category, List<Assignments> assignment) {
		double score = 0;

		int total_assignments = 0;
		double total_points = 0;

		for (Assignments a : assignment) {
			if (a.getSubject().equals(subject)) {
				if (a.getStudentName().equals(student) && a.getAssignmentCategory()
						.substring(0, a.getAssignmentCategory().length() - 2).equals(category)) {
					total_assignments += 1;
					total_points += a.getPoints();
				}
			}
		}

		if (total_assignments == 0) {
			score = 0;
		} else {
			DistributionsDao dao = new DistributionsDao();
			Map<String, Integer> distributions = dao.getAllDistributions();
			score = ((distributions.get(category) / total_assignments) * total_points) / 100;
		}

		return score;
	}

	public static void main(String[] args) {

		
//		GeneralDao gdao=new GeneralDao();
//		boolean createTables = gdao.createTables();
//		System.out.println(createTables);
		
		
		
		App app = new App();
		AssignmentsDao adao = new AssignmentsDao();
		DistributionsDao ddao = new DistributionsDao();
		List<Assignments> a = adao.getAllAssignments();
		Set<String> st = new HashSet<String>();
		for (Assignments aa : a) {
			st.add(aa.getStudentName());
		}
		List<String> students = new ArrayList<String>();
		for (String s : st) {
			students.add(s);
		}

		Set<String> sub = new HashSet<String>();
		for (Assignments ab : a) {
			sub.add(ab.getSubject());
		}
		List<String> subjects = new ArrayList<String>();
		for (String s : sub) {
			subjects.add(s);
		}
		
		Set<String> cat = new HashSet<String>();
		for (Assignments ab : a) {
			cat.add(ab.getAssignmentCategory().substring(0,ab.getAssignmentCategory().length()-2));
		}
		List<String> categories = new ArrayList<String>();
		for (String s : cat) {
			categories.add(s);
		}

		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Enter 1 :Compute & display student average score per Assignments category & overall  rating for assigned subject(s).");
		System.out.println(
				"Enter 2 :Compute & Display subject average score per Assignments category & overall rating for assigned student(s).");
		System.out.println("Enter 3 :Basic features");
		String input1 = sc.nextLine();
		if (input1.equals("2")) {
			System.out.println("Enter student name : ");
			String input2 = sc.nextLine();
			if (!students.contains(input2)) {
				System.out.println("Invalid name");
			} else {
				for (String s : subjects) {
					System.out.print(s + "\t");
					
					for(String c:categories) {
						System.out.print(app.getScore(input2, s, c, a) + "\t");
					}
					System.out.println(app.getOverAllRating(input2, s, a));
				}
			}
		} else if (input1.equals("1")) {
			System.out.println("Enter subject");
			String input2 = sc.nextLine();
			if (!subjects.contains(input2)) {
				System.out.println("Invalid subject");
			} else {

				for (String n : students) {
					System.out.print(n + "\t");
					
					for(String c:categories) {
						System.out.print(app.getScore(n, input2, c, a) + "\t");
					}
					System.out.println(app.getOverAllRating(n, input2, a));
				}

			}
		} else if (input1.equals("3")) {
			System.out.println("Enter a:Add Assignments to an existing list");
			System.out.println("Enter b:Remove Assignments to an existing list");
			System.out.println("Enter c:Add Assignments category with weights to an existing list");
			System.out.println("Enter d:Remove Assignments category with weights to an existing list");
			System.out.println("Enter e:Display all Assignments categories with their weights");
			System.out.println("Enter f:CRUD operations for student enrollment to subject(s)");
			System.out.println("Enter g:CRUD operations for enrolling  Assignments(s)  to a student");
			String input2 = sc.nextLine();
			if (input2.equals("a")) {
				int serialNo;
				String studentName;
				String subject;
				String assignmentCategory;
				String dateOfSubmission;
				int points;
				System.out.println("Enter serial number :");
				serialNo = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter student name :");
				studentName = sc.nextLine();
				System.out.println("Enter subject :");
				subject = sc.nextLine();
				System.out.println("Enter assignment category :");
				assignmentCategory = sc.nextLine();
				System.out.println("Enter date of submission :");
				dateOfSubmission = sc.nextLine();
				System.out.println("Enter points :");
				points = sc.nextInt();

				Assignments tempAssignments = new Assignments(serialNo, studentName, subject, assignmentCategory,
						dateOfSubmission, points);
				boolean addAssignments = adao.addAssignments(tempAssignments);
				if (addAssignments) {
					System.out.println("Successfull");
				} else {
					System.out.println("Operation failed");
				}

			} else if (input2.equals("b")) {

				System.out.println("Enter serial number of assignment to delete :");
				int sn = sc.nextInt();
				if (adao.removeAssignment(sn)) {
					System.out.println("Successfull");
				} else {
					System.out.println("Operation failed!  Please check and try again");
				}

			} else if (input2.equals("c")) {

				String category;
				int weight;
				System.out.println("Enter new category");
				category = sc.nextLine();
				System.out.println("Enter its weight");
				weight = sc.nextInt();
				Distributions dd = new Distributions();
				dd.setAssignmentCategory(category);
				dd.setWeight(weight);
				if (ddao.addDistributions(dd)) {
					System.out.println("Successfull");
				} else {
					System.out.println("Operation failed!  Please check and try again");
				}

			} else if (input2.equals("d")) {
				System.out.println("Enter category name to delete");
				String category = sc.nextLine();
				if (ddao.removeCategory(category)) {
					System.out.println("Successfull");
				} else {
					System.out.println("Operation failed!  Please check and try again");
				}

			} else if (input2.equals("e")) {
				Map<String, Integer> distributions = ddao.getAllDistributions();
				System.out.println(distributions);
			} else if (input2.equals("f")) {
				System.out.println("Enter 1: to enroll student in a subject");
				System.out.println("Enter 2: to delete student from a subject");
				System.out.println("Enter 3: to update students information in a subject");
				System.out.println("Enter 4 to list all the students enrolled in a subject");
				String input3=sc.nextLine();
				if(input3.equals("1")) {
					SubjectDao subDao=new SubjectDao();
					Student stu=new Student();
					
					stu.setSubjectName("History");
					stu.setStudentName("Sohail");
					stu.setCategory("quiz");
					boolean enroll = subDao.enroll(stu);
					System.out.println(enroll);
				}else if(input3.equals("2")) {
					SubjectDao subDao=new SubjectDao();
					Student stu=new Student();
					
					stu.setSubjectName("History");
					stu.setStudentName("Sohail");
					stu.setCategory("quiz");
					boolean deleteStudent = subDao.deleteStudent(stu);
					System.out.println(deleteStudent);
				}else if(input3.equals("3")) {
					SubjectDao subDao=new SubjectDao();
					Student stu=new Student();
					
					stu.setSubjectName("History");
					stu.setStudentName("Ustad");
					stu.setCategory("quiz");
					boolean updateStudent = subDao.updateStudent(stu);
					System.out.println(updateStudent);
				}else if(input3.equals("4")) {
					SubjectDao subDao=new SubjectDao();
					List<Subject> list=subDao.getAllStudents("History");
					for(Subject s:list) {
						System.out.println(s.getStudentName()+"/"+s.getCategory());
					}
				}else {
					System.out.println("Invalid input please try again");
				}
			} else if (input2.equals("g")) {
				
				System.out.println("Enter 1: to assing a new work to student");
				System.out.println("Enter 2: to delete assinged work from a student");
				System.out.println("Enter 3: to update assinged work");
				System.out.println("Enter 4 to list all the assingments of a particular student");
				String input3=sc.nextLine();
				if(input3.equals("1")) {
					StudentDao sdao=new StudentDao();
					Subject subject=new Subject();
					subject.setStudentName("usman");
					subject.setSubjectName("Maths");
					subject.setCategory("test_3");
					boolean assing = sdao.assing(subject);
					System.out.println(assing);
				}else if(input3.equals("2")) {
					StudentDao sdao=new StudentDao();
					Subject subject=new Subject();
					subject.setStudentName("usman");
					subject.setSubjectName("Maths");
					subject.setCategory("test_3");
					boolean deleteAssignment = sdao.deleteAssignment(subject);
					System.out.println(deleteAssignment);
				}else if(input3.equals("3")) {
					StudentDao sdao=new StudentDao();
					Subject subject=new Subject();
					subject.setDateOfSubmission("12-sep-22");
					subject.setPoints(56);
					subject.setStudentName("usman");
					subject.setSubjectName("Maths");
					subject.setCategory("test_3");
					boolean updateAssignment = sdao.updateAssignment(subject);
					System.out.println(updateAssignment);
				}else if(input3.equals("4")) {
					StudentDao sdao=new StudentDao();
					List<Student> list=sdao.getAllAssignmentsList("Usman");
					for(Student s:list) {
						System.out.println(s.getSubjectName()+"/"+s.getCategory()+"/"+s.getDateOfSubmission()+"/"+s.getPoints());
					}
				}else {
					System.out.println("Invalid input please try again");
				}
				
				
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("Wrong input please try again");
		}

		sc.close();

	}
}
