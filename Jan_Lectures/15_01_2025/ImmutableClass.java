import java.io.*;
/*
final class Student implements Serializable
{
	private final int rollNo;
	private final String name;
	private final int standard;
	public Student(int rn, String n, int s)
	{
		rollNo = rn;
		name = n;
		standard = s;
	}
	public int getRollNo()
	{
		return rollNo;
	}
	public String getName()
	{
		return name;
	}

	public int getStandard()
	{
		return standard;
	}
	public String toString()
	{
		return "Student [Roll No :" + rollNo + ", Name: " + name + ". Standard: " + standard + "]";
	}
	public int hashCode()
	{
		return rollNo;
	}
	public boolean equals(Object obj)
	{
		Student s1 = (Student) obj;
		if( (this.rollNo == s1.rollNo) && (this.name.equals(s1.name)) && (this.standard == s1.standard) )
			return true;	
		else
			return false;
 	}
}
*/

/* Used Below record method for above class student to make it immutable */

/*
//cannonical constructor
record Student(int rollNo, String name, int standard) {}
*/

// Immutable Class
record Student(int rollNo, String name, int standard) {

	static String schoolName = "KV";
	// For Default Constructor
	Student() {
		// only static variable possible
		// schoolName = "KV";
		this(13, "Karan", 7);
	}

	Student(int rollNo, String name, int standard) {
		this.rollNo = rollNo;
		this.name = name;
		this.standard = standard;
	}

	// Additional Methods To Add
	public void display() {
		System.out.println("SchoolName: " + schoolName);
	}

	// Overiding on existing methods
	public boolean equals(Student obj) {
		if (this.rollNo == (obj).rollNo) {
			return true;
		}
		return false;
	}

	// Overiding on existing methods
	public int hashCode() {
		return this.rollNo;
	}
}

class Transaction {
	int fromAcctNo;
	int toAccNo;
	String ifsc;
	int amount;
}

public class ImmutableClass {
	public static void main(String args[]) {
		Student s1 = new Student(11, "Rinku", 5);
		Student s2 = new Student(12, "Pinku", 5);
		Student s3 = new Student();

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());

		System.out.println(s1.equals(s2));

		s1.display();
		s2.display();
		s3.display();
	}

}