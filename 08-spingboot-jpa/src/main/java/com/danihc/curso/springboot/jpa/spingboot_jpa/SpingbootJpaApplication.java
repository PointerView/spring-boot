package com.danihc.curso.springboot.jpa.spingboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.danihc.curso.springboot.jpa.spingboot_jpa.dto.PersonDto;
import com.danihc.curso.springboot.jpa.spingboot_jpa.entities.Person;
import com.danihc.curso.springboot.jpa.spingboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpingbootJpaApplication implements CommandLineRunner{

	@Autowired
	PersonRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(SpingbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		update();
	}


	@Transactional(readOnly=true)
	public void whereIn(){
		List<Person> registers = repository.getPersonsByIds();
		registers.forEach(System.out::println);

		List<Person> registers2 = repository.getPersonsByIdsParam(Arrays.asList(4L,5L,6L));
		registers2.forEach(System.out::println);
	}


	@Transactional(readOnly=true)
	public void queriesFuntionAggregation(){

		Long count = repository.totalPerson();
		Long min = repository.minId();
		Long max = repository.maxId();

		System.out.println("total=" + count + ", minId=" + min + ", maxId=" + max);

		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> System.out.println("name=" + reg[0] + ", length=" + reg[1]));

		Long maxLength = repository.getPersonMaxNameLength();
		Long minLength = repository.getPersonMinNameLength();
		System.out.println("maxLength=" + maxLength + ", minLength=" + minLength);
	}

	@Transactional(readOnly=true)
	public void subQueries(){
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});
	}


	@Transactional(readOnly=true)
	public void personalizedQueriesBetween(){
		List<Person> namesBetween = repository.findAllBetweenId();
		namesBetween.forEach(System.out::println);

		List<Person> namesBetween2 = repository.findAllBetweenId2("K", "P");
		namesBetween2.forEach(System.out::println);

		List<Person> namesBetween3 = repository.findByNameBetween("K", "P");
		namesBetween3.forEach(System.out::println);

		List<Person> namesBetween4 = repository.findByIdBetween(2L, 4L);
		namesBetween4.forEach(System.out::println);

		List<Person> namesBetween5 = repository.findByNameBetweenOrderByNameAsc("F", "P");
		namesBetween5.forEach(System.out::println);
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesConcatUpperAndLowerCase(){
		List<String> fullConcatNames = repository.findAllFullNameConcatLower();
		fullConcatNames.forEach(System.out::println);

		List<String> fullConcatNames2 = repository.findAllFullNameConcatUpper();
		fullConcatNames2.forEach(System.out::println);
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesDistinct(){
		System.out.println("Consultas con nombres unicos de personas ");
		List<String> names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("Consultas con nombres unicos de personas ");
		List<String> programmingLanguage = repository.findAllProgrammingLanguageDistinct();
		programmingLanguage.forEach(System.out::println);
	}

	@Transactional
	public void personalizedQueries2(){

		System.out.println("==================== consulta por objeto persona y lenguaje de programacion ====================");
		List<Object[]> personsRegs = repository.findAllMixPerson();
		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});
		
		System.out.println("Consulta que puebla y devuelve objeto entiti de una instancia personalizada");
		List<Person> persons = repository.findAllPersonalizedObjectPerson();
		persons.forEach(System.out::println);
	
		System.out.println("Consulta que puebla y devuelve objeto dto de una clase personalizada");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	
	}

	@Transactional
	public void personalizedQueries(){

		Scanner sc = new Scanner(System.in); 
		System.out.println("==================== consulta solo el nombre por el id ====================");
		System.out.print("Ingrele el id para el nombre: ");
		Long id = sc.nextLong();

		String name = repository.getNameById(id);
		System.out.println(name);

		Long name2 = repository.getIdById(id);
		System.out.println(name2);

		String name3 = repository.getFullNameById(id);
		System.out.println(name3);
		
		sc.close();
	}

	@Transactional
	public void delete(){

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrele el id de la persona a eliminar: ");
            Long id = sc.nextLong();
            
            repository.deleteById(id);
            
            repository.findAll().forEach(System.out::println);
        }
	}

	@Transactional
	public void delete2(){

		Scanner sc = new Scanner(System.in);
		repository.findAll().forEach(System.out::println);
		System.out.print("Ingrele el id de la persona a eliminar: ");
		Long id = sc.nextLong();

		Optional<Person> optPerson = repository.findById(id);
		optPerson.ifPresentOrElse(repository::delete,
			() -> System.out.println("Ese usario no existe"));

		repository.findAll().forEach(System.out::println);
		sc.close();
	}

	@Transactional
	public void update(){

		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el id de la persona: ");
		Long id = sc.nextLong();
		Optional<Person> optPerson = repository.findById(id);

		optPerson.ifPresent(p -> {
			System.out.println(p);
			System.out.print("Ingrese un nuevo lenguaje  para cambiar " + p.getProgrammingLanguage() + ": ");
			String newLanguage = sc.next();
			p.setProgrammingLanguage(newLanguage);

			Person personDb = repository.save(p);
			System.out.println(personDb);
		});
		sc.close();
	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingrese el nombre: ");
		String name = scanner.next();

		System.out.print("Ingrese el apellido: ");
		String lastname = scanner.next();

		System.out.print("Ingrese el lenguaje de programacion: ");
		String programmingLanguage = scanner.next();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
		
		scanner.close();
	}

	@Transactional(readOnly=true)
	public void findOne(){

		Optional<Person> optPerson = repository.findById(3L);
		optPerson.ifPresent(System.out::println);

		Optional<Person> optPerson2 = repository.findOne(4L);
		optPerson2.ifPresent(System.out::println);

		Optional<Person> optPerson3 = repository.findOneName("Pepe");
		optPerson3.ifPresent(System.out::println);

		Optional<Person> optPerson4 = repository.findOneLikeName("ia");
		optPerson4.ifPresent(System.out::println);

		Optional<Person> optPerson5 = repository.findByNameContaining("ia");
		optPerson5.ifPresent(System.out::println);
	}

	@Transactional(readOnly=true)
	public void list(){

		List<Person> persons = (List<Person>) repository.findAll();
		List<Person> persons2 = (List<Person>) repository.findByProgrammingLanguage("Java");
		List<Person> persons3 = (List<Person>) repository.buscarByProgrammingLanguage("Java");
		List<Person> persons4 = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");

		persons4.stream().forEach(System.out::println);

		List<Object[]> persons5 = repository.obtenerPersonData();
		persons5.forEach(p -> {
			System.out.println(p[0]+ " es experto en " + p[1]);
			System.out.println(p);
		});
	}
}
