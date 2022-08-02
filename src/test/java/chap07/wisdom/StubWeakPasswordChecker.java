package chap07.wisdom;

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
