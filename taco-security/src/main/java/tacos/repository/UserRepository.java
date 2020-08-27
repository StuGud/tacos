package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.User;

/**
 * Created by StuGud on 2020/8/26.
 */

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
