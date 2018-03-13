INSERT INTO c_security_permission (id, permission_label, permission_value) VALUES
('PORTAL_CLIENT', 'Halaman Portal Client', 'ROLE_PORTAL_CLIENT'),
('MANAGEMENT', 'Halaman Management', 'ROLE_MANAGEMENT');

INSERT INTO c_security_role (id, description, name) VALUES
('ADMINISTRATOR', 'Application Administrator', 'Administrator'),
('CLIENT', 'Client', 'Client');

INSERT INTO c_security_role_permission (id_role, id_permission) VALUES
('CLIENT', 'PORTAL_CLIENT'),
('ADMINISTRATOR', 'PORTAL_CLIENT'),
('ADMINISTRATOR', 'MANAGEMENT');

INSERT INTO c_security_user (id, active, username, fullname, email, id_role) VALUES
('admin', true, 'admin', 'Administrator', 'admin@mail.com', 'ADMINISTRATOR');

INSERT INTO c_security_user_password (id_user, password) VALUES
('admin', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');