create table respuestas (
    id bigint not null auto_increment,
    mensaje varchar(255) not null,
    topico_id bigint,
    creado datetime not null,
    usuario_apodo bigint,
    curso_id bigint,
    primary key (id),

    foreign key (topico_id) references topicos(id),
    foreign key (usuario_apodo) references usuarios(id),
    foreign key (curso_id) references cursos(id)
);
