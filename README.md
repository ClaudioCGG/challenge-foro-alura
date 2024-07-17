### CHALLENGE FORO ALURA ###
---

La presenta API REST tiene una funcionalidad que se va actualizando con mejoras. En principio la API REST de Foro tiene 4 entidades principales:

> [CURSO]
> La misma cuenta con una funcionalidad CRUD, donde pueden crearse nuevos cursos, con una descripción String con la propiedad `nombre` y una `categoría` que es un enum para unificar temáticas.

  
 ** Request de ejemplo: **
```
{
    "nombre": "Foro 1707 951 prueba",
    "categoria": 2
}
```

> [TOPICO]
> La misma cuenta con una funcionalidad CRUD, donde pueden crearse nuevos Topicos, con una descripción String con la propiedad `titulo` y un `mensaje`. La propiedad `cursoId` esta linkeada con la entidad Curso.

  
 ** REQUEST de ejemplo:**
```
{
    "titulo": "Problema con Hibernate",
    "mensaje": "Tengo un problema al configurar Hibernate con Spring Boot.",
    "cursoId": 2
}
```

 *** RESPONSE de ejemplo: En la respuesta nos devuelve de manera automática, la fecha de `creado`, el `estado` y el `autorId` de la persona que creo el Topico***
```

{
	"id": 101,
	"titulo": "Ayuda Curso JPA",
	"mensaje": "Tengo un problema al configurar mi entidad",
	"creado": "2024-07-17T18:37:38.1892191",
	"estado": "NUEVO",
	"autorId": 5,
	"cursoId": 5
}

```

> [RESPUESTA]
> En Desarrollo

  
 *** Request de ejemplo:***
```
{
    "nombre": "Foro 1707 951 prueba",
    "categoria": 2
}
```

> [USUARIO]
> la misma cuenta con una funcionalidad CRUD, donde pueden crearse nuevos cursos, con una descripción String con la propiedad `nombre` y una `categoría` que es un enum para unificar temáticas.

  
 *** Request de ejemplo:***
```
{
    "mensaje": "Tengo un problema al configurar Hibernate con Spring Boot.",
    "topicoId": 122,
    "categoria": 2
}
```

---
## Autores
Claudio CGG


