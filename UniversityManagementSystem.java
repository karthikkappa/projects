// Project 1
// Karthik Kappagantu, Kris Lacey

import java.util.Scanner;

public class UniversityManagementSystem {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Student students[] = new Student[2000];
		Faculty faculty[] = new Faculty[2000];
		Staff staff[] = new Staff[1000];
		
		int studentCount = 0;
		int facultyCount = 0;
		int staffCount = 0;
		
		System.out.println("Welcome to my Personal Management Program");
		System.out.println();		
		System.out.println("Choose one of the options:\n"
				+ "1- Enter the information a faculty\n"
				+ "2- Enter the information of a student\n"
				+ "3- Print tuition invoice for a student\n"
				+ "4- Print faculty information\n"
				+ "5- Enter the information of a staff member\n"
				+ "6- Print the information of a staff member\n"
				+ "7- Exit Program");
		int option = sc.nextInt();
		
		while(option != 7)
		{
			switch(option)
			{
			case 1:
				System.out.println("Enter the faculty info:\n");
				System.out.println("Name of the faculty");
				String name = sc.next();
				String name1 = sc.next();
				String fullName = name + " " + name1;
				System.out.println("ID:");
				String id = sc.next();
				System.out.println("Rank:");
				String rank = sc.next();
				while (!(rank.equalsIgnoreCase("Professor") || (rank.equalsIgnoreCase("Adjunct")))) {
	            	System.out.printf("%s is invalid\n", rank);
		            rank = sc.next();
	            }
				System.out.println("Department:");
				String department = sc.next();
				while (!(department.equalsIgnoreCase("Mathematics") || (department.equalsIgnoreCase("Engineering")) || (department.equalsIgnoreCase("Sciences")))) {
	            	System.out.printf("%s is invalid\n", department);
		            department = sc.next();
	            }
				System.out.println();
				System.out.println("Faculty added!");
				System.out.println();
				faculty[facultyCount++] = new Faculty(fullName, id, rank, department);
				System.out.println();
				break;
			case 2:
				System.out.println("Enter the student info:\n");
				System.out.println("Name of Student:");
				String name2 = sc.next();
				String name3 = sc.next();
				String fullName1 = name2 + " " + name3;
				System.out.println("ID:");
				String id1 = sc.next();
				System.out.println("GPA:");
				double gpa = sc.nextDouble();
				System.out.println("Credit hours");
				int hours = sc.nextInt();
				System.out.println();
				System.out.println("Student added!");
				System.out.println();
				students[studentCount++] = new Student(fullName1, id1, gpa, hours);
				break;
			case 3:
				System.out.println("Enter the student’s id:");
				String id5 = sc.next();
				boolean matchFlag = false;
				for(int i = 0; i <= students.length - 1; i++)
				{
					Student std1 = students[i];
					if ((std1 != null && std1.getId().equals(id5)))
					{
						matchFlag = true;
						students[i].print();
						break;
					}
					
				}
				if (!matchFlag)
				{
					System.out.println();
					System.out.println("No Student member matched!");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Enter the Faculty’s id:");
				String id6 = sc.next();
				boolean flag = false;
				for(int i = 0; i <= students.length - 1; i++)
				{
					Faculty fac = faculty[i];
					if ((fac != null && fac.getId().equals(id6)))
					{
						flag = true;
						faculty[i].print();
						break;
					}
					
				}
				if (!flag)
				{
					System.out.println();
					System.out.println("No Faculty member matched!");
					System.out.println();
				}
				break;
			case 5:
				System.out.println("Enter the information of a staff member:\n");
				System.out.println("Name of the staff member:");
				String name4 = sc.next();
				String name5 = sc.next();
				String fullName2 = name4 + " " + name5;
				System.out.println("ID:");
				String id2 = sc.next();
				System.out.println("Department:");
				String department1 = sc.next();
				while (!(department1.equalsIgnoreCase("Mathematics") || (department1.equalsIgnoreCase("Engineering")) || (department1.equalsIgnoreCase("Sciences")))) {
	            	System.out.printf("%s is invalid\n", department1);
		            department1 = sc.next();
				}
				System.out.println("Status, Enter P for Part Time, or Enter F for Full Time");
				char status = sc.next().charAt(0);
				System.out.println();
				System.out.println("Staff member added!");
				System.out.println();
				staff[staffCount++] = new Staff(fullName2, id2, department1, status);
				System.out.println();
				break;
			case 6:
				System.out.println("Enter the Staff’s id:");
				String id7 = sc.next();
				boolean match = false;
				for(int i = 0; i <= staff.length - 1; i++)
				{
					Staff sta = staff[i];
					if ((sta != null && sta.getId().equals(id7)))
					{
						match = true;
						staff[i].print();
						break;
					}
					
				}
				if (!match)
				{
					System.out.println();
					System.out.println("No Staff member matched!");
					System.out.println();
				}
				break;
			}
			
			System.out.println("Choose one of the options:\n"
					+ "1- Enter the information a faculty\n"
					+ "2- Enter the information of a student\n"
					+ "3- Print tuition invoice for a student\n"
					+ "4- Print faculty information\n"
					+ "5- Enter the information of a staff member\n"
					+ "6- Print the information of a staff member\n"
					+ "7- Exit Program");
			option = sc.nextInt();
		}
		System.out.println("Goodbye!");
	}

}

abstract class Person
{
	String fullName;
	String id;
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract void print(); 
}

class Student extends Person
{
	double gpa;
	int hours;
	
	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Student(double gpa, int hours) {
		super();
		this.gpa = gpa;
		this.hours = hours;
	}
	public Student(String fullName, String id, double gpa, int hours)
	{
		super();
		this.fullName = fullName;
		this.id = id;
		this.gpa = gpa;
		this.hours = hours;
	}

	@Override
	public void print()
	{
        double total = (hours * 236.45) + 52;
        double totalRounded = Math.round(total * 10000) / 10000.0;
        double total2 = 0;
        if (gpa >= 3.85)
        	totalRounded = Math.round((totalRounded * 0.75) * 10000) / 10000.0;
        	double total1 = (hours * 236.45) + 52;
        	total1 = total1 - totalRounded;
        	total2 = Math.round(total1 * 10000) / 10000.0;
        System.out.println();
        System.out.println("Here is the tuition invoice for " + fullName);
        System.out.println();
		System.out.println("-------------------------------------------------------------------------");
        System.out.println(fullName + "                " + id);
        System.out.println();
        System.out.println("Credit Hours: " + hours + "   ($236.45/credit hour)");
        System.out.println();
        System.out.println("Fees: $52");
        System.out.println();
        System.out.println();
        System.out.println("Total payment (after discount): $" + totalRounded + "        ($" + total2 + " discount applied)");
        System.out.println("-------------------------------------------------------------------------");
        
	}
}

abstract class Employee extends Person
{
	String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}

class Faculty extends Employee
{
	String rank;
	
	public Faculty(String rank) {
		super();
		this.rank = rank;
	}

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Faculty(String department, String rank)
	{
		this.department = department;
		this.rank = rank;
	}
	public Faculty(String fullName, String id, String rank, String department)
	{
		super();
		this.fullName = fullName;
		this.id = id;
		this.rank = rank;
		this.department = department;
	}
	

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public void print() 
	{
		{
	        System.out.println("----------------------------------------------------------------------");
	        System.out.println(fullName + "         " + id);
	        System.out.println(department + " Department, " + rank);
	        System.out.println("----------------------------------------------------------------------");
	    }
	}
}

class Staff extends Employee
{
	char status;
	
	public Staff(String fullName, String id, String department, char status)
	{
		super();
		this.fullName = fullName;
		this.id = id;
		this.department = department;
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public void print() 
	{
		String status1 = null;
		if (status == 'p' || status == 'P')
			status1 = "Part Time";
		if (status == 'f' || status == 'F')
			status1 = "Full Time";
		System.out.println("----------------------------------------------------------------------");
        System.out.println(fullName + "         " + id);
        System.out.println(department + " Department, " + status1);
        System.out.println("----------------------------------------------------------------------");
	}
	
}




