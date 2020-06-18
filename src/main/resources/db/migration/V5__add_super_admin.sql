insert into users (email, password, login)
values ('gook.goro@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Pupok');

with super_admin_user_id as (select currval('users_id_seq'))
insert into user_role(role, user_id)
values ('SUPER_ADMIN', (select * from super_admin_user_id));


