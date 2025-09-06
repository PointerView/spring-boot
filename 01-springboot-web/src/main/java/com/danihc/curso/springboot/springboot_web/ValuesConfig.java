package com.danihc.curso.springboot.springboot_web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // Indica que es una clase de configuracion
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8") /*Añadir a la configuracion del proyecto el 
path de un fichero .properties y asi poder injectar los valores con @Value, eso se hace porque solo esta añadido por
defecto el application.porperties, si se crean nuevos ficheros properties has de añadirse al contexto del proyecto de
este modo.

Si queremos agregar mas de uno, se usa PropertySources({xxxxxx, xxxxxx}) y se agregan en forma de lista con las llaves*/
public class ValuesConfig {

}
