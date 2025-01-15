/*
class var		// Not Allowed
{
}
class A
{
	var i = 0;		// Not Allowed
}
*/

public class Variables {
	public static void main(String args[]) {
		var a = "Hii"; // var is used to declare a variable, assign data type automatically which is assigned to the variable first time
		System.out.println(a);
		// a = 20; // Gives Error
		System.out.println(a);
		a = "Helloooo";
		System.out.println(a);

		// Only allowed as a local variable inside main class
		float var = 3.14f;
		System.out.println(var);

		var arr = new int[5];

		// Only Below data types are possioble for the switch choice (int , string ,
		// enum)
		int num = 1;
		String choice = "1.1";
		enum Month {
			JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;
		}
		switch (Month.MAR) // Accepts int, String enum data type as a input
		{
			/*
			 * case JAN,FEB,MAR :
			 * System.out.println("ONE");
			 * break;
			 * case APR,MAY,JUN :
			 * System.out.println("ONE");
			 * break;
			 * case JUL,AUG,SEP :
			 * System.out.println("ONE");
			 * break;
			 * case OCT,NOV,DEC :
			 * System.out.println("ONE");
			 * break;
			 * default:System.out.println("DEFAULT");
			 */

			// Both above and below code has same functionality with added arrow features in
			// the new java version for break statements

			case JAN, FEB, MAR -> System.out.println("SPRING");
			case APR, MAY, JUN -> System.out.println("SUMMER");
			case JUL, AUG, SEP -> System.out.println("RAINY");
			case OCT, NOV, DEC -> System.out.println("WINTER");
			default -> System.out.println("DEFAULT");
		}

		// switch return value is assigned to the weather
		String weather = switch (Month.NOV) {
			case JAN, FEB, MAR -> "SPRING";
			case APR, MAY, JUN -> "SUMMER";
			case JUL, AUG, SEP -> "RAINY";
				case OCT, NOV, DEC -> "WINTER";
			default -> "DEFAULT";
		};
		System.out.println(weather);
	}
}
