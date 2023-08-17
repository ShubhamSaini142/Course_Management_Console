import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Course {
	private String name;
	private int courseId;
	private String description;
	private double price;
	private Professor professor;
	private static List<Student> enrolledStudents;
	private Map<Student, int[]> studentAssignmentMarks;
	public static ArrayList<Course> courses1 = new ArrayList<>();

	public Course(String name, int courseId, String description, double price) {
		this.name = name;
		this.courseId = courseId;
		this.description = description;
		this.price = price;
		Course.enrolledStudents = new ArrayList<>();
		this.studentAssignmentMarks = new HashMap<>();
	}


	public void enrollStudent(Student student) {
		enrolledStudents.add(student);
		studentAssignmentMarks.put(student, new int[6]);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public static List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Student student) {
		Course.enrolledStudents.add(student);
	}

	public Map<Student, int[]> getStudentAssignmentMarks() {
		return studentAssignmentMarks;
	}

	public void setAssignmentMarks(Student student, int[] marks) {

		studentAssignmentMarks.put(student, marks);

	}

	public static void viewCourses(List<Course> courses) {
		if (courses.isEmpty()) {
			System.out.println("No courses are available.☹️");
		} else {
			System.out.println("Available Courses:");
			for (Course course : courses) {
				System.out.println("Course ID: " + course.getCourseId());
				System.out.println("Course Name: " + course.getName());
				System.out.println("Description: " + course.getDescription());
				System.out.println("Price: $" + course.getPrice());
				System.out.println("Professor: "
						+ (course.getProfessor() != null ? course.getProfessor().getUsername() : "Not assigned"));
				System.out.println();
				System.out.println("------------------------------");
			}

		}
	}
	public static void createCourse(Scanner scanner, List<Course> courses) {
		scanner.nextLine(); 
		System.out.print("Enter Course Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Course ID: ");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter Course Description: ");
		String description = scanner.nextLine();
		System.out.print("Enter Course Price: ");
		double price = scanner.nextDouble();

		Course newCourse = new Course(name, courseId, description, price);
		courses.add(newCourse);
		courses1.add(newCourse);
		System.out.println("Course created successfully!😀");
	}


}
