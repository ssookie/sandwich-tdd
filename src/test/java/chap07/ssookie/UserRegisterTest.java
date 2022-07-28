package chap07.ssookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, spyEmailNotifier);
    }

    /**
     * 스텁 (Stub): 값을 셋팅하여 원하는 값을 바로 얻도록 한다.
     */
    @DisplayName("1. 암호가 약하면 회원가입 실패")
    @Test
    void test1() {
        // given. 암호가 약하다고 응답하도록 설정, 실제 동작하는 구현은 필요하지 않다. -> 스텁 사용
        stubWeakPasswordChecker.setWeak(true);

        // when & then
        assertThrows(WeakPasswordException.class,
                () -> {
                    userRegister.register("id", "password", "eamil@email.com");
                });
    }

    /**
     * 가짜 (Fake) : DB에 구애받지 않도록, 메모리로 구현한다.
     */
    @DisplayName("2. 중복된 회원가입이면 회원가입 실패")
    @Test
    void test2() {
        // given. 이미 가입된 회원을 만든다.
        memoryUserRepository.save(new User("id", "password", "eamil@email.com"));

        // when & then
        assertThrows(DuplicationException.class, () -> {
            userRegister.register("id", "password", "eamil@email.com");
        });
    }

    @DisplayName("3. 암호가 약하지 않고, 중복된 회원이 없으면 회원가입 성공")
    @Test
    void test3() {
        userRegister.register("id", "password", "eamil@email.com");

        User user = memoryUserRepository.findById("id");
        assertEquals("id", user.getId());
    }

    /**
     * Spy 객체 : 호출 내역을 기록한다.
     */
    @DisplayName("4. 회원가입 후 이메일 전송")
    @Test
    void test4() {
        userRegister.register("id", "password", "eamil@email.com");

        // then. 이메일 발송을 요청했는지, 발송한 이메일 주소 확인
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("eamil@email.com", spyEmailNotifier.getEmail());
    }
}
