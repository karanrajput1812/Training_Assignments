import java.util.regex.*;

public class PatternMatching
{
	public static void main(String args[])
	{
		String s1 = "Wissen Technology";
		String s2 = "Techy";
		
		// System.out.println(s1.contains(s2));

		Pattern p1 = Pattern.compile(s2);
		Matcher m1 = p1.matcher(s1);

		System.out.println(s1 + " contains " + s2 + ":" + m1.find());

		System.out.println("------------------");
		
		String email = "abc@.com";
		String s3 = "[a-zA-Z].*@.*\\.com";
		Pattern p2 = Pattern.compile(s3);
		Matcher m2 = p2.matcher(email);	
		System.out.println(m2.matches());

		System.out.println("------------------");
		
		long mobile_no = 8989221131L;
		String s4 = "[8-9][0-9]{9}";
		Pattern p3 = Pattern.compile(s4);
		Matcher m3 = p3.matcher(mobile_no+"");	
		System.out.println(m3.matches());

		System.out.println("------------------");
		
		String s5 = "ae";
		String s6 = "[a-d][b-f]";
		String s7 = "[a-d&&[b-f]]";
		Pattern p4 = Pattern.compile(s6);
		Pattern p5 = Pattern.compile(s7);
		Matcher m4 = p4.matcher(s5);	
		Matcher m5 = p5.matcher(s5);	

		System.out.println(m4.matches());
		System.out.println(m5.matches());

		System.out.println("------------------");

				
		String s8 = "c";
		String s9 = "[a-d][b-f]";
		String s10 = "[a-d&&[b-f]]";
		Pattern p6 = Pattern.compile(s9);
		Pattern p7 = Pattern.compile(s10);
		Matcher m6 = p6.matcher(s8);	
		Matcher m7 = p7.matcher(s8);	

		System.out.println(m6.matches());
		System.out.println(m7.matches());

		System.out.println("------------------");


		
		

	}
}
