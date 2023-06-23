package com.AE.linearAlgebraAuto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LinearAlgebraAutoApplicationTests {

    @Test
    void contextLoads() {
        Double a = 1D;
        System.out.println(a);
        Double b = a;
        b = 2D;
        System.out.println(a);
        System.out.println(b);
    }

}
