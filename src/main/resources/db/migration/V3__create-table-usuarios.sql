create table usuarios (
    id bigint not null auto_increment,
    mail varchar(255) not null unique,
    clave varchar(500) not null,
    nombre_apellido varchar(255) not null,
    apodo varchar(255),
    activo boolean not null,
    alta datetime not null,
    foto_perfil varchar(255),
    perfil varchar(255) not null,

    primary key (id)
);