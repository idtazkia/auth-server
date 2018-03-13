package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface RoleDao extends PagingAndSortingRepository<Role, String> {

    Role findByName(String role);

}
