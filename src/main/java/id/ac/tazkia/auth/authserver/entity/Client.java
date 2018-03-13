package id.ac.tazkia.auth.authserver.entity;

import id.ac.tazkia.auth.authserver.constant.GrantType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "oauth_client_details")
public class Client extends BaseEntity{
    
    @NotEmpty
    @Column(nullable = false)
    private @Getter @Setter String appName;
    
    @NotEmpty
    @Column(nullable = false,unique = true)
    private @Getter @Setter String clientId;
    
    @NotEmpty
    @Column(name="resource_ids", nullable = false)
    private @Getter @Setter String resourceId;
    
    @NotEmpty
    @Column(nullable = false)
    private @Getter @Setter String clientSecret;
    
    @NotEmpty
    @Column(nullable = false)
    private @Getter @Setter String scope;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private @Getter @Setter GrantType authorizedGrantTypes;
    
    @NotEmpty
    @Column(name = "web_server_redirect_uri", nullable = false)
    private @Getter @Setter String redirectUri;
    
    private @Getter @Setter String authorities;
    private @Getter @Setter int accessTokenValidity;
    private @Getter @Setter int refreshTokenValidity;
    private @Getter @Setter String additionalInformation;
    
     @Column(name = "autoapprove")
    private @Getter @Setter String autoApprove;
    
}
