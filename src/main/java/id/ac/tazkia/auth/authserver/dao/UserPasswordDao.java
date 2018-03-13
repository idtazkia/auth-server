package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.UserPassword;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String> {
    
}
