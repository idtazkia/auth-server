package id.ac.tazkia.auth.authserver.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseEntity implements Serializable{
    
    @Id
    @GeneratedValue(generator = "system-uuid2")
    @GenericGenerator(name = "system-uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(36)")
    private @Getter @Setter String id;
    
}
