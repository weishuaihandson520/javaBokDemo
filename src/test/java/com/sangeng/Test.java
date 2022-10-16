package com.sangeng;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class Test {

    @org.junit.jupiter.api.Test
    public  void test1(){
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("15862947755");
        System.out.println(encode);

//        String code="$2a$10$OppPzC0Cyy6yZ6XXO.Yeue7cJuL2dD6vWHyiz.1WtK0pNoCpG.u.q";
//        boolean matches = passwordEncoder.matches("15862947755", code);
//        System.out.println(matches);


    }
}
