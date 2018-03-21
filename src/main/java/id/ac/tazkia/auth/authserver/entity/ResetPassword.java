package id.ac.tazkia.auth.authserver.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class ResetPassword extends BaseEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    private String code;

    @Column(columnDefinition = "DATE")
    @NotNull
    private LocalDate expired;
}
