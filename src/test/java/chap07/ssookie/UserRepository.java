package chap07.ssookie;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
