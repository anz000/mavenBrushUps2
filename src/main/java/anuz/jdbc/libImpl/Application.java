
package anuz.jdbc.libImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import anuz.maven.jdbc.*;

/**
 * @author anz
 *
 */
public class Application {

	// Employee Services Layer Instance
	private static EmployeeServices eServices = new EmployeeServicesImpl();

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Intializing Employee Operations usng Collection operations");

		while (true) {
			// the option from the menu
			int selection = displayAndSelectMenu();

			switch (selection) {
			case 1:
				createEmployee();
				break;

			case 2:
				readEmployee();
				break;

			case 3:
				updateEmployee();
				break;

			case 4:
				deleteEmployee();
				break;

			case 5:
				calcGrossSalary();
				break;

			case 6:
				calcHRA();
				break;

			case 7:
				groupByGender();
				break;

			case 8:
				sortByID();
				break;

			case 9:
				sortByName();
				break;

			case 10:
				sortBySalary();
				break;

			case 11:
				groupByAvSalary();
				break;

			case 12:
				setDummyData();
				break;
				
			case 13:
				getAllInfo();
				break;

			case 14:
				System.out.println(" Exiting Now.. Bye!! ");
				System.exit(0); // terminates the program
				break;
				
			default:
				System.out.println(" Wrong Selection. Please select from the menu.");
				break;
			}
		}

	}

	/**
	 * This method populates the employee list with dummy data for testing purpose {DEPRECATED}
	 */
	private static void setDummyData() {
		try {
			eServices.createEmployee(1001, "Mark", 7000.0, "Male");
			eServices.createEmployee(1002, "Anuj", 91000.0, "Male");
			eServices.createEmployee(100, "Simone", 88000.0, "Female");
			eServices.createEmployee(11, "Aaron", 7500.0, "Other");
			eServices.createEmployee(21, "Steven", 55000.0, "Male");
			eServices.createEmployee(3001, "Wilson", 12000.0, "Female");
			eServices.createEmployee(4401, "Varrg", 16000.0, "Female");
			eServices.createEmployee(601, "Mikael", 6000.0, "Female");
			eServices.createEmployee(1201, "Kevin", 27000.0, "Male");
		} catch (InvalidSalaryException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method displays the menu and then returns the selected option
	 * @return (int) the selected option
	 */
	private static int displayAndSelectMenu() {
		System.out.println();
		System.out.println(" *** **** MENU **** ***");
		System.out.println(" 1. Create Employee");
		System.out.println(" 2. Read Employee");
		System.out.println(" 3. Update Employee");
		System.out.println(" 4. Delete Employee");
		System.out.println(" 5. Calculate Gross Salary");
		System.out.println(" 6. Calculate HRA");
		System.out.println(" 7. Group by Gender");
		System.out.println(" 8. Sort by ID");
		System.out.println(" 9. Sort By Name");
		System.out.println(" 10. Sort by Salary");
		System.out.println(" 11. Group by Salary");
		System.out.println(" 12. Set Dummy data {DEPRECATED}");
		System.out.println(" 13. Show All Employee List");
		System.out.println(" 14. Exit");

		int selection = 0;
		Scanner scan = new Scanner(System.in);
		try {
			if (scan.hasNextInt())
				selection = scan.nextInt();
		} catch (Exception e) {
			scan.nextLine();
			System.out.println(" Exception occurred !! " + e.getMessage());
		}
		return selection;
	}

	/**
	 * This method asks for employee details and add it to the list of employees
	 * Handles Invalid Salary exception if the salary is less than 5000
	 */
	private static void createEmployee() {

		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("Please enter the new employee details");
			System.out.println("ID?");
			int id = scan.nextInt();

			System.out.println("Name?");
			String name = scan.next();

			System.out.println("Salary?");
			double salary = scan.nextDouble();

			System.out.println("Gender?");
			String gender = scan.next();

			Employee temp = eServices.createEmployee(id, name, salary, gender);

			System.out.println("Succesfully created !!");
			System.out.println(temp);
			// } catch (InvalidSalaryException e) {
			// System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method asks for an ID and finds the corresponding employee Handles
	 * exception if the ID is not found
	 */
	private static void readEmployee() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter the ID of the employee that you want to read");
			int rId = scan.nextInt();
			Employee temp = eServices.readEmployee(rId); // find the employee with the ID from the array
															// and return it
			System.out.println(temp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method asks for employee iD and updates the properties for that employee
	 * Handles exception if Employee ID is not found or salary is less than 5000
	 */
	private static void updateEmployee() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter the ID of the employee that you want to update");
			int updateId = scan.nextInt();

			System.out.println("Type name.");
			String uName = scan.next();

			System.out.println("Type salary.");
			double uSalary = scan.nextDouble();

			System.out.println("Type gender.");
			String uGender = scan.next();

			// updates the given employee with new info and returns the whole array
			eServices.updateEmployee(updateId, uName, uSalary, uGender);
		} catch (EmployeeNotFoundException | InvalidSalaryException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method asks for employee ID and then deletes it Handles exception if id not found
	 */
	private static void deleteEmployee() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter the Id of employee to be deleted");
			int deleteId = scan.nextInt();

			eServices.deleteEmployee(deleteId);// deletes the employee with the ID and returns the array
		} catch (EmployeeNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method calculates the gross salary
	 */
	private static void calcGrossSalary() {
		try {
			System.out.println("The Gross salary of employees : " + eServices.calculateGrossSalary());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method calculates the HRA
	 */
	private static void calcHRA() {
		try {
			System.out.println("The HRA @" + Employee.reimburseHRA + " : " + eServices.calculateHRA());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method groups the employees by the unique values in gender
	 */
	private static void groupByGender() {
		Map<String, Integer> grpGenderEList;
		try {
			grpGenderEList = eServices.groupByGender();
			System.out.println(grpGenderEList);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method sorts the employees by ID in ascending order
	 */
	private static void sortByID() {
		// setDummyData();
		List<Employee> temp = null;
		try {
			temp = eServices.sortById();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		temp.forEach(System.out::println);
	}

	/**
	 * This method sorts the employees by age in ascending order
	 */
	private static void sortByName() {
		// setDummyData();
		List<Employee> temp = null;
		try {
			temp = eServices.sortByName();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		temp.forEach(System.out::println);
	}

	/**
	 * This method sorts the employees by salary in ascending order
	 */
	private static void sortBySalary() {
		// setDummyData();
		List<Employee> temp = null;
		try {
			temp = eServices.sortBySalary();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		temp.forEach(System.out::println);
	}

	/**
	 * This method divides the employee list into below average and equal/above
	 * average
	 */
	private static void groupByAvSalary() {
		Map<String, Integer> grpAvSalaryEList;
		try {
			grpAvSalaryEList = eServices.groupByAvSalary();
			System.out.println(grpAvSalaryEList);
		} catch (SQLException e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will get all the info about the employees
	 */
	private static void getAllInfo() {
		List<Employee> empList = new ArrayList<>();
		try {
			empList = eServices.getAllEmployees();
			System.out.println(empList);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
