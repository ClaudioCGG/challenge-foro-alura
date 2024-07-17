create table topicos (
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje varchar(255) not null,
    creado datetime,
    estado varchar(255) not null,
    usuario_apodo bigint,
    curso_id bigint,
    primary key (id),

    foreign key (usuario_apodo) references usuarios(id),
    foreign key (curso_id) references cursos(id)
);
