package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.ResetPassword;
import id.ac.tazkia.auth.authserver.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResetPasswordDao extends PagingAndSortingRepository<ResetPassword, String> {

    ResetPassword findByUser(User u);

    ResetPassword findByCode(String code);
}
