package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PermissionDao extends PagingAndSortingRepository<Permission, String> {

    Permission findByPermissionLabel(String Label);

    Permission findByPermissionValue(String Value);

}
