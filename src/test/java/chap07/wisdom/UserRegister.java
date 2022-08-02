package chap07.wisdom;

import chap07.User;

public class UserRegister {
    private WeakPasswordChecker weakPasswordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;
    public UserRegister(StubWeakPasswordChecker stubWeakPasswordChecker, MemoryUserRepository memoryUserRepository, SpyEmailNotifier spyEmailNotifier) {
        this.weakPasswordChecker = stubWeakPasswordChecker;
        this.userRepository = memoryUserRepository;
        this.emailNotifier = spyEmailNotifier;
    }

    public void register(String id, String password, String email) {
        if (weakPasswordChecker.weakPasswordCheck(password)) {
            throw new WeakPasswordException();
        }
        // 중복 회원 체크
        if (userRepository.findById(id) != null) {
            throw new DuplicationException();
        }
        // 회원가입
        userRepository.save(new User(id, password, email));
        emailNotifier.sendEmail(email);

    }
}
