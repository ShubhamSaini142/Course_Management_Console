import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
	private String username;
	private String password;
	public static List<String[]> loginCredentials = new ArrayList<>();

	public Student(String username, String password) {
		this.username = username;
		this.password = password;
		loginCredentials.add(new String[] { username, password });
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public static Student createStudent(ArrayList<Student> students, Scanner scanner) {
		System.out.print("Enter Student Username: ");
		String username = scanner.next();
		System.out.println();
		System.out.print("Enter Student Password: ");
		String password = scanner.next();
		System.out.println();
		Student student = new Student(username, password);
		students.add(student);
		System.out.println("Student created successfully! 😀 ");
		return student;
	}

	public void viewEnrolledCourses(List<Course> courses) {
		if (Course.courses1.isEmpty()) {
			System.out.println("You are not enrolled in any courses ☹️.");
		} else {
			System.out.println("Enrolled Courses:");
			for (Course course : Course.courses1) {
				if (Course.getEnrolledStudents().contains(this)) {
					System.out.println("Course ID: " + course.getCourseId());
					System.out.println("Course Name: " + course.getName());
					System.out.println("Description: " + course.getDescription());
					System.out.println("Professor: "
							+ (course.getProfessor() != null ? course.getProfessor().getUsername() : "Not assigned"));
					System.out.println("------------------------------");
				}
			}
		}
	}

	public void enrollInCourse(List<Course> courses, Student student, Scanner scanner) {
		Course.viewCourses(Course.courses1);
		System.out.print("Enter the Course ID to enroll: ");
		int courseIdToEnroll = scanner.nextInt();

		Course selectedCourse = null;
		for (Course course : Course.courses1) {
			if (course.getCourseId() == courseIdToEnroll) {
				selectedCourse = course;
				break;
			}
		}
		if (selectedCourse != null) {
			if (Course.getEnrolledStudents().contains(this)) {
				System.out.println("You are already enrolled in this course.");
			} else {
				List<Student> enrolledStudents = new ArrayList<>(Course.getEnrolledStudents());
				System.out.println(enrolledStudents);
				enrolledStudents.add(student);
				selectedCourse.setEnrolledStudents(student);
				System.out.println("Successfully enrolled in the course! 😀 ");
			}
		} else {
			System.out.println("Course with the specified ID not found. ☹️");
		}
	}
	public void viewAssignmentScores(List<Course> courses) {
		boolean foundCourseWithAssignments = false;
		for (Course course : Course.courses1) {
			boolean flag = false;
			for (Student std : Course.getEnrolledStudents()) {
				if (std.getUsername().equals(this.getUsername())) {
					flag = true;
				}
			}
			if (flag) {
				int[] assignmentMarks = null;
				for (var entry : course.getStudentAssignmentMarks().entrySet()) {
					if (this.getUsername().equals(entry.getKey().getUsername())) {
						assignmentMarks = entry.getValue();
					}
				}
				if (assignmentMarks != null) {
					System.out.println("Course Name: " + course.getName());
					System.out.println("Assignment Scores for " + getUsername() + ":");
					for (int i = 0; i < assignmentMarks.length; i++) {
						System.out.println("Assignment " + (i + 1) + ": " + assignmentMarks[i]);
					}
					System.out.println("------------------------------");
					foundCourseWithAssignments = true;
				}
			}
		}
		if (!foundCourseWithAssignments) {
			System.out.println("No assignment scores available for your enrolled courses. ☹️ ");
		}
	}

}