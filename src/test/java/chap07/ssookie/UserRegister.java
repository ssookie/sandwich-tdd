package chap07.ssookie;

// 회원가입 하는 실제 로직이 있는 class
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
        // 암호가 약한지 체크
        if (weakPasswordChecker.weakPasswordCheck(password)) {
            throw new WeakPasswordException();
        }

        // 중복된 회원인지 체크
        if (userRepository.findById(id) != null) {
            throw new DuplicationException();
        }

        // 회원 가입
        userRepository.save(new User(id, password, email));

        // 회원 가입 후 이메일 전송
        emailNotifier.sendEmail(email);
    }
}
