package chap07.ssookie;

import java.util.HashMap;
import java.util.Map;

// Fake. 메모리로 DB 역할을 구현한다.
public class MemoryUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
