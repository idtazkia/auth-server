package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.UserPassword;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String> {

    Optional<UserPassword> findById(String id);
}
