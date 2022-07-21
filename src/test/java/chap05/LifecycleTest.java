package chap05;

import org.junit.jupiter.api.*;

public class LifecycleTest {

    public LifecycleTest() {
        System.out.println("new LifecycleTest!");
    }

    // BeforeAll은 객체 생성 전에 먼저 실행된다. 
    @BeforeAll
    static void beforeAll() {
        System.out.println("[@BeforeAll] === beforeAll()");
    }

    @BeforeEach // BeforeEach 여러개이면 메서드명 오름차순으로 실행된다.
    void beforeEach() {
        System.out.println("[@BeforeEach] === beforeEach()");
    }

    @Test
    void testA() {
        System.out.println("test A started.");
    }

    @Test
    void testB() {
        System.out.println("test B started.");
    }

    @AfterEach
    void after() {
        System.out.println("[@AfterEach] === after()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("[@AfterAll] === afterAll()");
    }
}
