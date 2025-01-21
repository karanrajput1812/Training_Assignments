import java.io.IOException;

public class lang_Demo extends Thread {
    public static void main(String[] args) throws IOException, InterruptedException {
        // String s1 = "Hi";;
        // System.out.println(s1);

        // s1 = "Hello";
        // System.out.println(s1);

        // s1 = s1.toUpperCase();
        // System.out.println(s1);

        // StringBuffer sb1 = new StringBuffer("Hello");
        // sb1.append(" World");
        // System.out.println(sb1);

        Runtime r = Runtime.getRuntime();
        System.out.println("Wait and see the magic........in 10 seconds from now");
        Thread.sleep(10000);
        Process p1 = r.exec("calc.exe");
        Thread.sleep(5000);
        Process p2 = r.exec("mspaint.exe");
        Thread.sleep(5000);
        Process p3 = r.exec("notepad.exe");
        System.out.println("Just wait for another 10 seconds to watch it disappearing");
        Thread.sleep(10000);
        p3.destroy();
        Thread.sleep(5000);
        p2.destroy();
        Thread.sleep(5000);
        
    }
}
