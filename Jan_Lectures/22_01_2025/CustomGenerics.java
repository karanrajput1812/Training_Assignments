class A<T>{
    public void add(T a, T b) {
        System.out.println("Generic Logic");
    }    
}
public class CustomGenerics {
    public static void main(String[] args) {
        A<String> a1 = new A<String>();
        a1.add("good","morning");

        A<Integer> a2 = new A<Integer>();
        a2.add(89,88);

        A<Double> a3 = new A<Double>();
        a3.add(8.9,8.8);
    }
}
