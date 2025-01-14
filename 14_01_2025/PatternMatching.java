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

		
		

	}
}
