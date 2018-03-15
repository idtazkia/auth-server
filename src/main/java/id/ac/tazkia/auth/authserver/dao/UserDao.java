package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {

    public Page<User> findByusernameContainingIgnoreCase(String username, Pageable page);
}
