import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor {
	private String username;
	private String password;
	private Course course;
	private Course assignedCourse;
	public static List<String[]> loginCredentials = new ArrayList<>();

	public Professor(String username, String password) {
		this.username = username;
		this.password = password;
		this.course = null; 
		loginCredentials.add(new String[] { username, password });

	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getAssignedCourse() {
		return assignedCourse;
	}

	public void setAssignedCourse(Course assignedCourse) {
		this.assignedCourse = assignedCourse;
	}

	public static void createProfessor(ArrayList<Professor> professors, Scanner scanner) {
		System.out.print("Enter Professor Username: ");
		System.out.println();
		String username = scanner.next();
		System.out.print("Enter Professor Password: ");
		String password = scanner.next();
		System.out.println();
		professors.add(new Professor(username, password));
		System.out.println("Professor created successfully! 😀");
	}

public static void assignCourseToProfessor(Scanner scanner, List<Course> courses, List<Professor> professors) {
		if (Course.courses1.isEmpty()) {
			System.out.println("No courses are available to assign. ☹️");
			return;
		}
		System.out.println("Available Courses:");
		for (int i = 0; i < courses.size(); i++) {
			Course course = Course.courses1.get(i);
			System.out.println((i + 1) + ". " + course.getName() + " (Course ID: " + course.getCourseId() + ")");
		}
		System.out.print("Enter the number of the course to assign: ");
		int c = scanner.nextInt();
		if (c > 0 && c <= Course.courses1.size()) {
			Course selectedCourse = Course.courses1.get(c - 1);

			if (selectedCourse.getProfessor() != null) {
				System.out.println(
						"The course is already assigned to Professor " + selectedCourse.getProfessor().getUsername());
				return;
			}
			System.out.println("Available Professors:");
			for (int i = 0; i < professors.size(); i++) {
				Professor professor = professors.get(i);
				System.out.println((i + 1) + ". " + professor.getUsername());
			}
			System.out.print("Enter the number of the professor to assign: ");
			int pc= scanner.nextInt();

			if (pc > 0 && pc <= professors.size()) {
				Professor selectedProfessor = professors.get(pc - 1);
				if (selectedProfessor.getCourse() != null) {
					System.out.println(
							"Professor " + selectedProfessor.getUsername() + " is already assigned to a course.");
					return;
				}
				selectedCourse.setProfessor(selectedProfessor);
				selectedProfessor.setCourse(selectedCourse);
				System.out.println("Course assigned to Professor " + selectedProfessor.getUsername() + " successfully!");
			} else {
				System.out.println("Invalid professor choice.  failed.");
			}
		} else {
			System.out.println("Invalid course choice.  failed.");
		}
	}
public static void giveMarksToStudent(List<Course> courses, Professor professor, Scanner scanner) {
		System.out.println("Select a course to give marks:");
		List<Course> assignedCourses = new ArrayList<>();

		for (Course course : Course.courses1) {
			if (course.getProfessor() != null && course.getProfessor().getUsername().equals(professor.getUsername())) {
				assignedCourses.add(course);
				System.out.println(assignedCourses.size() + ". " + course.getName());
			}
		}
		if (assignedCourses.isEmpty()) {
			System.out.println("No courses assigned to the professor.☹️");
			return;
		}
		System.out.print("Enter the number of the course to give marks: ");
		int cc = scanner.nextInt();
		if (cc > 0 && cc <= assignedCourses.size()) {
			Course selectedCourse = assignedCourses.get(cc - 1);
			System.out.println("Enrolled Students for " + selectedCourse.getName() + ":");
			List<Student> enrolledStudents = Course.getEnrolledStudents();
			for (int i = 0; i < enrolledStudents.size(); i++) {
				System.out.println((i + 1) + ". " + enrolledStudents.get(i).getUsername());
			}
			System.out.print("Enter the number of the student to give marks: ");
			int sc = scanner.nextInt();
			if (sc > 0 && sc <= enrolledStudents.size()) {
				Student selectedStudent = enrolledStudents.get(sc - 1);
				System.out.println("Enter the marks for the student (ass1 to ass2):");
				int[] marks = new int[2];
				for (int i = 0; i < marks.length; i++) {
					System.out.print("Assignment " + (i + 1) + ": ");
					marks[i] = scanner.nextInt();
				}
				selectedCourse.setAssignmentMarks(selectedStudent, marks);
				System.out.println("Marks assigned to student " + selectedStudent.getUsername() + " successfully!");
			} else {
				System.out.println("Invalid student choice. Mark assignment failed.☹️");
			}
		} else {
			System.out.println("Invalid course choice. Mark assignment failed.☹️");
		}
	}


}