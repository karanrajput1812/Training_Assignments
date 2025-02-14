import java.util.StringTokenizer;

public class SplitString {
    public static void main(String[] args) {
        String str = "1,Karan,23,200000.00,CEO";

        StringTokenizer st = new StringTokenizer(str,",");
        int count = st.countTokens();

        for(int i=0; i<count; i++)
        {
            System.out.println(st.nextToken());
        }
    }
}
