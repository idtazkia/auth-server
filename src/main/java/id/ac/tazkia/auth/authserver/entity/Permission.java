package id.ac.tazkia.auth.authserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dekul
 */

@Entity
@Table(name = "c_security_permission")
public class Permission extends BaseEntity{
    
    @NotEmpty(message = "Label tidak boleh kosong")
    @Column(name = "permission_label", nullable = false, unique = true, length = 100)
    private  @Getter @Setter String permissionLabel;
    
    @NotEmpty(message = "value tidak boleh null")
    @Column(name = "permission_value", nullable = false, unique = true, length = 50)
    private  @Getter @Setter String permissionValue;
    
}
