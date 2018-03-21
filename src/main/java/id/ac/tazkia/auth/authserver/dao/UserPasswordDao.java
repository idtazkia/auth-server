package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.User;
import id.ac.tazkia.auth.authserver.entity.UserPassword;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;


public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String> {

    Optional<UserPassword> findById(String id);

    UserPassword findByUser(@NotNull User user);
}
