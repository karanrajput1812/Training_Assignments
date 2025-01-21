import java.util.Scanner;
import java.lang.reflect.*;

class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}

public class calculation {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        int x, y;
        String operation;
        String className;

        System.out.println("Welcome to the calculator");
        System.out.println("Please enter the class name");
        className = sc.nextLine();

        Class c1 = Class.forName(className);
        Object obj = c1.newInstance();

        System.out.println("Enter operation as per below mentioned methods present in the : " + className);
        Method methods[] = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " ");
        }
        do {
            System.out.println("Enter the operation: ");
            operation = sc.next();
            if(operation.equals("0")) {
                break;
            }
            System.out.println("Enter the first number: ");
            x = sc.nextInt();
            System.out.println("Enter the second number: ");
            y = sc.nextInt();
            Object result = null;
            try {
                Method method = c1.getMethod(operation, int.class, int.class);
                result = method.invoke(obj, x, y);
                if(result == null) {
                    throw new NoSuchMethodException();
                }
                System.out.println("Result of " + operation + " is: " + result);
            } catch (NoSuchMethodException e) {
                System.out.println("Invalid operation");
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            System.out.println("\nEnter 0 to exit or");
        } while(true);
        sc.close();
    }
}
