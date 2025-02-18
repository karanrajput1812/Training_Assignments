//package com.example.display;
//
//import java.util.Comparator;
//
//import com.example.model.Emp;
//
//public class DisplayByAge implements Comparator<Emp> {
//    public int compare(Emp s1, Emp s2) {
//        return Integer.compare(s1.age, s2.age);
//    }
//}


package com.example.display;

import java.util.Comparator;

import com.example.model.Emp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisplayByAge implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Integer.compare(s1.age, s2.age);
    }

    @Bean("display_by_age")
    public DisplayByAge getDisplayByAge() {
        return new DisplayByAge();
    }
}
