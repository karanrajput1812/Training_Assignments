import java.io.*;

public class PrintStreamDemo {
    public static void main(String[] args) {
        try {

            // PrintWriter pw = new PrintWriter(System.out);
            // PrintWriter pw = new PrintWriter("abc.txt");  // add below string(pw.println -> ) to the file and remove whichever is present in the file is removed
            PrintWriter pw = new PrintWriter(new FileOutputStream("PrintStreamDemo.java", true)); // append below string to the last line of the file data
            pw.println("Hello Everybody");
            pw.flush();
            pw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
}
