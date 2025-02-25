import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializing {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "Karan";
        p1.age = 21;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
            oos.writeObject(p1);
    
            System.out.println("Successfully serializable........"); 
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
    }
}
