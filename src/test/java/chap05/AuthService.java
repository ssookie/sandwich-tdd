package chap05;

public class AuthService {
    public void authenticate(String auth) {
        // null Exception 처리를 위한 로직만 구현.
        if (auth == null) throw new IllegalArgumentException("auth");
    }
}
