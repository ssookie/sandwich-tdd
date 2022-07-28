package chap07.ssookie;

// Stub 대역 - 값을 셋팅하여 원하는 값을 바로 얻도록 한다.
public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean weakPasswordCheck(String password) {
        return weak;
    }
}
