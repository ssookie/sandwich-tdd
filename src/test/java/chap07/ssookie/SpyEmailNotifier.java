package chap07.ssookie;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called; // 이메일 발송 여부
    private String email;   // 발송한 이메일

    public boolean isCalled() {
        return called;
    }

    public String getEmail() {
        return email;
    }

    // Spy 객체 - 호출된 내역을 기록한다.
    @Override
    public void sendEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
