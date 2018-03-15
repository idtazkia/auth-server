package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionDao extends PagingAndSortingRepository<Permission, String> {

    Permission findByPermissionLabel(String Label);

    Permission findByPermissionValue(String Value);

    public Page<Permission> findBypermissionValueContainingIgnoreCase(String permissionValue, Pageable page);

}
