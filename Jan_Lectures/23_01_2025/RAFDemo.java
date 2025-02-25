import java.io.RandomAccessFile;

public class RAFDemo {
    public static void main(String[] args) {
        try {

            // To read and wti
            RandomAccessFile raf = new RandomAccessFile("employees.csv", "rws");
            // System.out.println(raf.readLine());

            // Goes to the 5 charcter indexing from 0
            raf.seek(5);
            // System.out.println(raf.readLine());
            // System.out.println(raf.readLine());

            raf.seek(raf.length());
            raf.writeBytes("Just Fro Demo");

            raf.seek(0); /// Compulsory to readLine from start
            raf.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
}
