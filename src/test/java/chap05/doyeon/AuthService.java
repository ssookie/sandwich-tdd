package chap05.doyeon;

public class AuthService {
    public void authenticate(String str) {
        if (str == null) throw new IllegalArgumentException("Exxception");
    }
}
