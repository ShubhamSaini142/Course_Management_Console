import java.util.ArrayList;

import java.util.Scanner;

public class Welcome {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ArrayList<Course> courses = new ArrayList<>();
		Professor loggedInProfessor = null;
		Student loggedInStudent = null;

		String adminUsername = "admin";
		String adminPassword = "admin123";

		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Professor> professors = new ArrayList<>();

		boolean loggedIn = false;
		int code = 0;

		while (!loggedIn) {
			System.out.println("WELCOME TO DIGIT COURSE MANAGEMENT SYSTEM!");
			System.out.println("                   (◑‿◐)                 ");
			System.out.println("1. 🧔 Admin Login");
			System.out.println();
			System.out.println("2. 🧑‍🎓 Student Login");
			System.out.println();
			System.out.println("3. 🧑🏻‍🏫 Professor Login");
			System.out.println();
			System.out.println("4. ➡️ Exit");
			System.out.println();
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			System.out.println("--------------------------------------------------");
			switch (choice) {
				case 1:
					System.out.print("Enter Admin Username🧔: ");
					String adminInputUsername = scanner.next();
					System.out.println();
					System.out.print("Enter Admin Password🧔: ");
					String adminInputPassword = scanner.next();
					System.out.println();
					if (adminUsername.equals(adminInputUsername) && adminPassword.equals(adminInputPassword)) {
						System.out.println("Admin logged in successfully!😀 ");
						System.out.println("--------------------------------------------");
						loggedIn = true;
						code = 1;
					} else {
						System.out.println("Invalid credentials. Try again.☹️");
					}
					break;

				case 2:
					System.out.print("Enter Student Username🧑‍🎓: ");
					String studentInputUsername = scanner.next();
					System.out.println();
					System.out.print("Enter Student Password🧑‍🎓: ");
					String studentInputPassword = scanner.next();
					if (checkStudentCredentials(studentInputUsername, studentInputPassword)) {
						System.out.println("Student logged in successfully!🧑‍🎓");
						loggedIn = true;
						loggedInStudent = new Student(studentInputUsername, studentInputPassword);
						code = 2;
					} else {
						System.out.println("Invalid credentials. Try again.");
					}
					break;

				case 3:
					System.out.print("Enter Professor Username🧑🏻‍🏫: ");
					String professorInputUsername = scanner.next();
					System.out.println();
					System.out.print("Enter Professor Password🧑🏻‍🏫: ");
					String professorInputPassword = scanner.next();
					if (checkProfessorCredentials(professorInputUsername, professorInputPassword)) {
						System.out.println("Professor logged in successfully!🧑🏻‍🏫");
						loggedIn = true;
						loggedInProfessor = new Professor(professorInputUsername, professorInputPassword);
						code = 3;
					} else {
						System.out.println("Invalid credentials. Try again.");
					}
					break;
				case 4:
					System.out.println("Exiting the Login System. Goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
					break;
			}
		}

		if (code == 1) {
			System.out.println("Welcome, Admin! 🧔 ");
			System.out.println();
			while (true) {
				System.out.println("1. Create Student");
				System.out.println();
				System.out.println("2. Create Professor");
				System.out.println();
				System.out.println("3. Create Course");
				System.out.println();
				System.out.println("4. Assign Course to Professor");
				System.out.println();
				System.out.println("5. Exit Admin Panel");
				System.out.println();
				int adminChoice = scanner.nextInt();
				switch (adminChoice) {
					case 1:
						loggedInStudent = Student.createStudent(students, scanner);
						System.out.println("--------------------------------------------");
						break;
					case 2:
						Professor.createProfessor(professors, scanner);
						System.out.println("--------------------------------------------");
						break;
					case 3:
						Course.createCourse(scanner, courses);
						System.out.println("--------------------------------------------");
						break;
					case 4:
						Professor.assignCourseToProfessor(scanner, courses, professors);
						System.out.println("--------------------------------------------");
						break;
					case 5:
						System.out.println("Exiting Admin Panel.");
						Welcome.main(null);
						System.out.println("--------------------------------------------");
						break;
					default:
						System.out.println("Invalid choice ☹️ . Please enter a valid option.");
						break;
				}

				if (!loggedIn) {
					break;
				}

			}
		}
		if (code == 2) {
			while (true) {
				System.out.println("Welcome, " + loggedInStudent.getUsername() + "!" + "🧑‍🎓");
				System.out.println();
				System.out.println("1. Enroll in Course");
				System.out.println();
				System.out.println("2. View Assignment Scores given by Professors");
				System.out.println();
				System.out.println("3. Logout");
				System.out.println();
				System.out.print("Enter your choice: ");
				int studentChoice = scanner.nextInt();
				System.out.println();
				switch (studentChoice) {
					case 1:
						loggedInStudent.enrollInCourse(courses, loggedInStudent, scanner);
						System.out.println("--------------------------------------------");
						break;
					case 2:
						loggedInStudent.viewAssignmentScores(courses);
						System.out.println("--------------------------------------------");
						break;
					case 3:
						System.out.println("Logging out...");
						Welcome.main(null);
						System.out.println("--------------------------------------------");
						return;
					default:
						System.out.println("Invalid choice ☹️ . Please enter a valid option.");
						break;
				}
				if (!loggedIn) {
					break;
				}
			}

		}
		if (code == 3) {
			while (true) {

				System.out.println("Welcome Professor!!! 😀 ");
				System.out.println();
				System.out.println("1. Give Marks to Student");
				System.out.println();
				System.out.println("2. Exit Professor Panel");
				Scanner scanner1 = new Scanner(System.in);
				int Professorchoice = scanner1.nextInt();
				System.out.println();
				switch (Professorchoice) {
					case 1:
						Professor.giveMarksToStudent(courses, loggedInProfessor, scanner);
						System.out.println("--------------------------------------------");
						break;
					case 2:
						System.out.println("Exiting Professor Panel.");
						Welcome.main(null);
						System.out.println("--------------------------------------------");
						break;
					default:
						System.out.println("Invalid choice ☹️ . Please enter a valid option.");
						break;
				}
				if (!loggedIn) {
					break;
				}

			}

		}
	}
	private static boolean checkStudentCredentials(String username, String password) {
		for (String[] credentials : Student.loginCredentials) {
			if (credentials[0].equals(username) && credentials[1].equals(password)) {
				return true;
			}
		}
		return false;
	}
	private static boolean checkProfessorCredentials(String username, String password) {
		for (String[] credentials : Professor.loginCredentials) {
			if (credentials[0].equals(username) && credentials[1].equals(password)) {
				return true;
			}
		}
		return false;
	}
}
