PRICE-API

Se implementa arquitectura hexagonal.  
La idea es aislar completamente la lógica de negocio del detalle de las implementaciones. Esto nos da la flexibilidad de
poder cambiar los adaptadores y, por ejemplo, pasar de REST a WSDL, o incluso tener más de una forma de interactuar con
el sistema, cambiar el tipo de base de datos o agregar un API como fuente de datos.  
Para esto separamos la lógica de negocio en un módulo aparte (core). Se definen de
esta forma límites "físicos" con los módulos inbound (estímulos de entrada al sistema) y outbound (salida a otros
sistemas y persistencia). El core sólo depende de la arquitectura, mientras que inbound y outbound dependen de core.  
Mediante puertos y adaptadores secundarios se hace depender al outbound del core. Cómo mejora, en el caso
del inbound, se podría aplicar la misma lógica con puertos primarios.
Se implementa también CQRS de forma parcial; es decir, implementamos el concepto de Query y Command para separar la
lógica que consulta al sistema de aquella que cambia su estado.  
Para aislar completamente el core de la implementación y del framework utilizado, se agregó un módulo de arquitectura
que implementa la integración con Spring Boot y abstrae la inyección de dependencias mediante un Service Locator. Este
módulo se podría extraer como una librería y sacarlo fuera del proyecto para reutilizarlo en otros.  
Finalmente, se incluye un Dockerfile para generar la imagen Docker de la app. Se podría hacer con el soporte a
Buildpacks brindado por Spring Boot, pero en mi opinión la configuración de docker debería estar separada de las
configuraciones de Maven, para separar lo referente al detalle de Ops de las configuraciones de Dev.
