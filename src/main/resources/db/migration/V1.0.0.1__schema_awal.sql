CREATE TABLE c_security_permission (
    id character varying(36) NOT NULL,
    permission_label character varying(100) NOT NULL,
    permission_value character varying(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (permission_label),
    UNIQUE (permission_value)
);

CREATE TABLE c_security_role (
    id character varying(36) NOT NULL,
    description character varying(50),
    name character varying(15) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE c_security_role_permission (
    id_role character varying(36) NOT NULL,
    id_permission character varying(36) NOT NULL,
    PRIMARY KEY (id_role, id_permission)
);

CREATE TABLE c_security_user_client(
    id_user character varying(36) NOT NULL,
    id_client character varying(36) NOT NULL,
    PRIMARY KEY (id_user, id_client)
);

CREATE TABLE c_security_user (
    id character varying(36) NOT NULL,
    active boolean,
    email character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    id_role character varying(36) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);

CREATE TABLE c_security_user_password (
    id_user character varying(36) NOT NULL,
    password character varying(255),
    PRIMARY KEY (id_user)
);

CREATE TABLE oauth_client_details (
  id character varying(36) NOT NULL,
  access_token_validity integer NOT NULL,
  app_name character varying(255) NOT NULL,
  additional_information character varying(255),
  authorities character varying(255),
  authorized_grant_types character varying(255) NOT NULL,
  autoapprove character varying(255) NOT NULL,
  client_id character varying(255) NOT NULL,
  client_secret character varying(255) NOT NULL,
  web_server_redirect_uri character varying(255) NOT NULL,
  refresh_token_validity integer NOT NULL,
  resource_ids character varying(255) NOT NULL,
  scope character varying(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (client_id)
);

CREATE TABLE oauth_client_token (
  token_id character varying(255),
  token BYTEA,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255)
);

CREATE TABLE oauth_access_token (
  token_id character varying(255),
  token BYTEA,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255),
  authentication BYTEA,
  refresh_token character varying(255)
);

CREATE TABLE oauth_refresh_token (
  token_id character varying(255),
  token BYTEA,
  authentication BYTEA
);

CREATE TABLE oauth_code (
  code character varying(255), 
  authentication BYTEA
);

CREATE TABLE oauth_approvals (
  user_id VARCHAR(255),
  client_id VARCHAR(255),
  scope VARCHAR(255),
  status VARCHAR(10),
  expires_at TIMESTAMP,
  lastModified_at TIMESTAMP
);

ALTER TABLE ONLY c_security_role_permission
    ADD CONSTRAINT fkg9os4isbs19ssfahravousxes FOREIGN KEY (id_role) REFERENCES c_security_role(id);

ALTER TABLE ONLY c_security_role_permission
    ADD CONSTRAINT fknqcv2qdac1phe20qqnyi6n1n FOREIGN KEY (id_permission) REFERENCES c_security_permission(id);

ALTER TABLE ONLY c_security_user_client
    ADD CONSTRAINT fkg9os4isbs19ssfahra192yhs FOREIGN KEY (id_user) REFERENCES c_security_user(id);

ALTER TABLE ONLY c_security_user_client
    ADD CONSTRAINT fknqcv2adqw9f11uh3fnvqfq31 FOREIGN KEY (id_client) REFERENCES oauth_client_details(id);

ALTER TABLE ONLY c_security_user
    ADD CONSTRAINT fke5ychpyk27l8vj47v36mrn0s1 FOREIGN KEY (id_role) REFERENCES c_security_role(id);

ALTER TABLE ONLY c_security_user_password
    ADD CONSTRAINT fk80arji7i1u0styufcy8b91it5 FOREIGN KEY (id_user) REFERENCES c_security_user(id);


