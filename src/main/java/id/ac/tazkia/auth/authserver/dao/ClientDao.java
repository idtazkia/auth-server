package id.ac.tazkia.auth.authserver.dao;

import id.ac.tazkia.auth.authserver.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientDao extends PagingAndSortingRepository<Client, String> {
    
    public Page<Client> findByappNameContainingIgnoreCase(String appName, Pageable page);
    
}
