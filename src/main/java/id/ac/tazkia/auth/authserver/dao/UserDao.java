package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {
    
    User findByUsername(String username);
    
}
