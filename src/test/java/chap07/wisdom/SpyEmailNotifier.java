package chap07.wisdom;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called; // 이메일 발송여부
    private String email;

    public String getEmail() {
        return this.email;
    }

    public boolean isCalled() {
        return called;
    }

    @Override
    public void sendEmail(String email) {
        this.called = true;
        this.email = email;

    }
}
