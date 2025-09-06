package com.danihc.curso.springboot.jpa.spingboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.danihc.curso.springboot.jpa.spingboot_jpa.dto.PersonDto;
import com.danihc.curso.springboot.jpa.spingboot_jpa.entities.Person;


/* Se indica en nombre de la clase entity a la que esta asociado el repositorio para luego saber
el tipo de retorno que tendra que hacer en los metodos overwrited, y el segundo es el tipo del ID
que usara para saber el tipo de dato en los parametros de findById de la clase entity */
public interface PersonRepository extends CrudRepository<Person, Long>{

    @Query("select p from Person p where p.id in ?1")
    public List<Person>  getPersonsByIdsParam(List<Long> ids);

    @Query("select p from Person p where p.id in (1,2,5)")
    public List<Person>  getPersonsByIds();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();


    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLength();

    @Query("select max(length(p.name)) from Person p")
    Long getPersonMaxNameLength();

    @Query("select min(length(p.name)) from Person p")
    Long getPersonMinNameLength();

    @Query("select count(p) from Person p")
    Long totalPerson();


    @Query("select min(p.id) from Person p")
    Long minId();

    @Query("select max(p.id) from Person p")
    Long maxId();


    List<Person> findByNameBetween(String s1, String s2);
    List<Person> findByIdBetween(Long l1, Long l2);
    List<Person> findByNameBetweenOrderByNameAsc(String s1, String s2);


    @Query("select p from Person p where p.id between 3 and 5")
    List<Person> findAllBetweenId();

    @Query("select p from Person p where p.name between ?1 and ?2")
    List<Person> findAllBetweenId2(String c1, String c2);

    @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select distinct p.name from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct p.programmingLanguage from Person p")
    List<String> findAllProgrammingLanguageDistinct();


    @Query("select new Person(p.name, p.lastname) from Person p") /*Instanciar manualmente el objeto para poder indicara los atributos a devolver
    mediante un constructor que debe de ser declarado en la clase, si no se hace asi, devolvera un array de Object. En este caso, los atributos
    no solicitados seran null*/
    List<Person> findAllPersonalizedObjectPerson();

    // Indicar la clase con el package al no ser una clase entity dentro del contexto de JPA
    @Query("select new com.danihc.curso.springboot.jpa.spingboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p") 
    List<PersonDto> findAllPersonDto();

    @Query("select p.name from Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id = ?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) from Person p where p.id = ?1")
    String getFullNameById(Long id);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage);/* En este caso el query method no
    funciona al no ser "buscar" una palabra reservada para queries a difenrencia de find, en este caso al
    ejecutar daria error y estariamos obligados a crear una consuta manual para que funcionara */

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id = ?1")
    Object[] obtenerPersonDataById(Long id);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name =?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    /******************************************************************************************************************************/

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name); /* findById spring lo identificia automaticamente porq esta declarado ya
    en la interfaz de CrudRepository pero los demas queryMethods personalizados deben ser declarados sus firmas en el repository */
}