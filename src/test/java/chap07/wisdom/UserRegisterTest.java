package chap07.wisdom;

import chap07.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    private UserRegister userRegister;

    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void test1() {
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "password", "email@email.com");
        });
    }

    @DisplayName("이미 가입된 회원인 경우 가입 실패")
    @Test
    void test2() {
        // 이미 가입된 회원 만듦
        memoryUserRepository.save(new User("id", "pw", "email@email.com"));

        assertThrows(DuplicationException.class, () -> {
            userRegister.register("id", "password", "email@email.com");
        });
    }
    @DisplayName("암호가 약하지 않고, 중복된 회원이 없으면 회원가입 성공")
    @Test
    void test3() {
        userRegister.register("id", "password", "email@email.com");

        User user = memoryUserRepository.findById("id");
        assertEquals("id", user.getId());
    }

    @DisplayName("회원가입하면 메일 전송")
    @Test
    void test4() {
        userRegister.register("id", "password", "email@email.com");

        // 이메일 발송을 요청ㅇ했는지, 발송한 이메일 주소 확인
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }

}
