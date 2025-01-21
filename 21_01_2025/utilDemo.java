import java.util.*;

public class utilDemo {

	public static void main(String args[]) {
		Date d1 = new Date();
		System.out.println(d1);
		System.out.println(d1.getTime());
		Date d2 = new Date();
		d2.setTime(-99939924922492374L);
		System.out.println(d2);

		// Months starts from the 0
		d1.setMonth(5); // set to the june
		d1.setMonth(48); // divide by 12 quotient is added to year and month is set based on the remainder
		
		// Gives differernce between the current year and 1900 (C.Y - 1900)
		System.out.println(d1.getYear());
		
		// Year mention in the date
		System.out.println(1900 + d1.getYear());

		System.out.println("--------------------");

		Stack s1 = new Stack();
		s1.push("111");
		s1.push("222");
		s1.push("333");
		s1.push("444");
		s1.push("555");

		// Return Position of the element in the stack form the top indexed from 1
		System.out.println(s1.search("222"));

		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.peek());
		System.out.println(s1.peek());
		System.out.println(s1.peek());

		System.out.println(s1.empty());

		System.out.println("--------------------");

		// Calender And GregorianCalender

		System.out.println("--------------------");

		BitSet b1 = new BitSet(16);
		BitSet b2 = new BitSet(16);

		for (int i = 0; i < 16; i++) {
			if (i % 3 == 0) {
				b1.set(i);
			}
			if (i % 4 == 0) {
				b2.set(i);
			}
		}

		System.out.println(b1);
		System.out.println(b2);

		// Intersection of both sets b1 and b2 -|
		// b1.and(b2);
		// Union of both sets b1 and b2 -|
		// b1.or(b2);
		// Union - Intersection -|
		// b1.xor(b2);
		System.out.println(b1);

	}
}