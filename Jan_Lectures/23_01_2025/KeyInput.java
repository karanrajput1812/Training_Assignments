import java.io.*;

public class KeyInput {
    public static void main(String[] args) {
        try {
            // Converts byte stream data to characters data
            InputStreamReader isr = new InputStreamReader(System.in);

            // Input into the buffer
            BufferedReader br = new BufferedReader(isr);

            System.out.println("Please Enter Your Name");
            String name = br.readLine();

            System.out.println("Enter age: ");
            int age = Integer.parseInt(br.readLine());

            System.out.println("Your good name is: " + name);
            System.out.println("Age after 10 years: " + (age + 10));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
