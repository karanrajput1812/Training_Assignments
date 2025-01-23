import java.io.*;
public class IODemo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter file name: ");
            String fname = br.readLine();
    
            File f = new File(fname);
            if(f.exists())
            {

                // Read all the data from the file with help of FileReader
                BufferedReader fr = new BufferedReader(new FileReader(fname));
                // To print all the lines present in the file by printing each line at a time till finds a null
                String line = null;
                while((line = fr.readLine()) != null)
                {
                    System.out.println(line);
                }
                fr.close();
            }
            else {
                System.out.println("Sorry! File Doesn't exist");
            }
            br.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        
    }   
}
