package id.ac.tazkia.auth.authserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "c_security_user")
public class User extends BaseEntity{
    
    @NotEmpty(message = "Username tidak boleh dikosongkan")
    @Column(nullable = false,unique = true)
    private @Getter @Setter String username;
    
    @NotEmpty(message = "Fullname tidak boleh dikosongkan")
    private @Getter @Setter String fullname;
    
    private @Getter @Setter Boolean active = Boolean.TRUE;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user", optional = true)
    @Cascade(CascadeType.ALL)
    private @Getter @Setter UserPassword userPassword;
    
    @Transient
    private @Getter @Setter String password;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private @Setter @Getter Role role;
    
    @NotEmpty(message = "Email tidak boleh dikosongkan")
    @Column(name = "email", unique = true, nullable = false)
    private @Getter @Setter String email;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @OrderBy("appName asc")
    @JoinTable(
        name="c_security_user_client", 
        joinColumns=@JoinColumn(name="id_user", nullable=false),
        inverseJoinColumns=@JoinColumn(name="id_client", nullable=false)
    )
    private Set<Client> clients = new HashSet<Client>();

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
