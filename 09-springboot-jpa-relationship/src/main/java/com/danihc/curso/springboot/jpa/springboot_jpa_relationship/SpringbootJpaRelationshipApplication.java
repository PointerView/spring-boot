package com.danihc.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.danihc.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository repoClient;

	@Autowired
	private InvoiceRepository repoInvoice;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	public void run(String... args) throws Exception {
		removeAddressById();
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Fran", "Moras");
		Address address1 = new Address("El Verjel", 1234); 
		Address address2 = new Address("Vasco de Gama", 1234); 

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		repoClient.save(client);

		Optional<Client> optionalClient = repoClient.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			repoClient.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void removeAddressById(){
		Optional<Client> optClient = repoClient.findById(2L);
		optClient.ifPresent(client -> {
			Address address1 = new Address("El Verjel", 1234); 
			Address address2 = new Address("Vasco de Gama", 1234); 
	
			client.setAddresses(Arrays.asList(address1, address2));
	
			repoClient.save(client);
			System.out.println(client);

			Optional<Client> optionalClient = repoClient.findOne(2L);
			optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address2);
			repoClient.save(c);
			System.out.println(c);
			});
		});
	}

	@Transactional
	public void oneToManyFindById(){
		Optional<Client> optClient = repoClient.findById(1L);
		optClient.ifPresent(client -> {
			Address address1 = new Address("El Verjel", 1234); 
			Address address2 = new Address("Vasco de Gama", 1234); 
	
			client.setAddresses(Arrays.asList(address1, address2));
	
			repoClient.save(client);
		});
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran", "Moras");
		Address address1 = new Address("El Verjel", 1234); 
		Address address2 = new Address("Vasco de Gama", 1234); 

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		repoClient.save(client);
		/*Aqui guardamos tanto el las address como el client de una sola vez ya que
		 * al tener cascadetype aplicado, al guardar el client, guarda automaticamente
		 * las address
		 */
	}

	@Transactional
	public void manyToOne(){
		Client client = new Client("Jhon", "Doe");
		client = repoClient.save(client);
		System.out.println(client);
		/*guardamos primero client y luego invoice porq no tiene cascadetype aplicado
		 * por ello se deben de guardar cada registro de manera manual
		 */
		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		invoice = repoInvoice.save(invoice);
		System.out.println(invoice);
	}

	@Transactional
	public void manyToOneFindById(){
		Optional<Client> client = repoClient.findById(1L);
		System.out.println(client);

		client.ifPresent(c -> {
			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(c);
			invoice = repoInvoice.save(invoice);
			System.out.println(invoice);

		});
	}
}
