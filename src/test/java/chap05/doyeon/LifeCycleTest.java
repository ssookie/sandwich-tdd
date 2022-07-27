package chap05.doyeon;

import org.junit.jupiter.api.*;

public class LifeCycleTest {

    public LifeCycleTest() {
        System.out.println("new LifecycleTest");
    }

    // BeforeAll 객체 생성 전에 실행됨.
    @BeforeAll
    static void BeforeAll() {
        System.out.println("[@BeforeAll] == BeforeAll()");

    }


    @AfterAll
    static void AfterAll() {
        System.out.println("[@AfterAll] == AfterAll()");
    }

    @BeforeEach
    void BeforeEach() {
        System.out.println("[@BeforeEach] == BeforeEach()");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("[@AfterEach] == AfterEach()");
    }

    @Test
    void testA() {
        System.out.println("test A started");
    }

    @Test
    void testB() {
        System.out.println("test B started");
    }


}
