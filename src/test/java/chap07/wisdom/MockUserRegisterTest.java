package chap07.wisdom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MockUserRegisterTest {

    private UserRegister userRegister;

    @Mock
    private WeakPasswordChecker weakPasswordChecker;

    @Mock
    EmailNotifier emailNotifier;

    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();


    @BeforeEach
    void setUp() {
        //userRegister = new UserRegister(weakPasswordChecker, memoryUserRepository, emailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void test1() {
        //stubWeakPasswordChecker.setWeak(true);
        BDDMockito.given(weakPasswordChecker.weakPasswordCheck("password")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "password", "email@email.com");
        });
    }

    @DisplayName("암호 체크 메서드 수행 확인")
    @Test
    void test2() {
        // 이미 가입된 회원 만듦
        userRegister.register("id", "password", "email@email.com");

        BDDMockito.then(weakPasswordChecker).should().weakPasswordCheck("password");

    }

    @DisplayName("가입하면 메일 전송")
    @Test
    void test3() {
        // 이미 가입된 회원 만듦
        userRegister.register("id", "password", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(emailNotifier).should().sendEmail(captor.capture());

        String email = captor.getValue();
        assertEquals("email@email.com", email);
    }


//    @DisplayName("암호가 약하지 않고, 중복된 회원이 없으면 회원가입 성공")
//    @Test
//    void test3() {
//        userRegister.register("id", "password", "email@email.com");
//
//        User user = memoryUserRepository.findById("id");
//        assertEquals("id", user.getId());
//    }
//
//    @DisplayName("회원가입하면 메일 전송")
//    @Test
//    void test4() {
//        userRegister.register("id", "password", "email@email.com");
//
//        // 이메일 발송을 요청ㅇ했는지, 발송한 이메일 주소 확인
//        assertTrue(spyEmailNotifier.isCalled());
//        assertEquals("email@email.com", spyEmailNotifier.getEmail());
//    }

}
