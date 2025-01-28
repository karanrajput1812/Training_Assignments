import java.io.*;

public class Deserializing {
    public static void main(String[] args) {
        
        try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"));
        Person obj = (Person) ois.readObject();
        System.out.println("Age: "+ obj.age);
        System.out.println("Name: " + obj.name);   
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
