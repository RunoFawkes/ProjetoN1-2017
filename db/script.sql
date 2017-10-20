drop table userprofile;
drop table comment;
drop table humor;
drop table post;
drop table usuario;
drop table comentario;



create table usuario(
    id_usuario bigint not null primary key generated always as identity(start with 1, increment by 1),
    username varchar(20) not null,
    password varchar(32) not null 
);

create table userprofile(
    id_userprofile bigint not null primary key,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(100),
    birthday date,
    location varchar(50),
    bio varchar(240),
    avatar blob
);

create table humor(
	id_humor bigint not null primary key generated always as identity(start with 1, increment by 1),
	cor varchar(50) not null,
	humor_nome varchar(50) not null
);

create table post(
    id_post bigint not null primary key generated always as identity(start with 1, increment by 1),
    post_text varchar(140) not null,
    post_date timestamp not null,
    fk_humor bigint not null,
    fk_usuario bigint not null
);

create table comentario(
    id_comment bigint not null primary key generated always as identity(start with 1, increment by 1),
    comment_text varchar(140) not null,
    comment_date timestamp not null,
    fk_usuario bigint not  null,
    fk_post bigint not null
);

alter table userprofile
add foreign key(id_userprofile)
references usuario(id_usuario)
on delete cascade;

alter table post
add foreign key(fk_usuario)
references usuario(id_usuario)
on delete cascade;

alter table post
add foreign key(fk_humor)
references humor(id_humor)
on delete cascade;

alter table comentario
add foreign key(fk_usuario)
references usuario(id_usuario)
on delete cascade;

alter table comentario
add foreign key(fk_post)
references post(id_post)
on delete cascade;