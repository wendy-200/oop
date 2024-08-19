package com.ups.oop.bootstrap;

import com.ups.oop.entity.*;
import com.ups.oop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStraData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;



    public BootStraData(PersonRepository personRepository, AnimalRepository animalRepository, StudentRepository studentRepository,
                        AuthorRepository authorRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.studentRepository = studentRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //person
        Person p1 = new Person();
        p1.setPersonId("0932467913");
        p1.setName("wendy");
        p1.setLastname("ortiz");
        p1.setAge(19);

        Person p2 = new Person();
        p2.setPersonId("0982765490");
        p2.setName("Mabelle");
        p2.setLastname("Mideros");
        p2.setAge(20);


        personRepository.save(p1);
        personRepository.save(p2);


        //Animals
        Animal a1 = new Animal();
        a1.setPetName("Sol");
        a1.setName("Bod");
        a1.setBread("Pitbull");
        a1.setColor("brown");
        a1.setWeight(20.1);
        a1.setHeight(3.2);
        a1.setLength(3.3);

        Animal a2 = new Animal();
        a2.setPetName("Perla");
        a2.setName("Simba");
        a2.setBread("Leonberger");
        a2.setColor("black");
        a2.setWeight(11.3);
        a2.setHeight(2.2);
        a2.setLength(3.2);

        Animal a3 = new Animal();
        a3.setPetName("Raul");
        a3.setName("Lucas");
        a3.setBread("Labrador");
        a3.setColor("white with black");
        a3.setWeight(16.6);
        a3.setHeight(4.1);
        a3.setLength(4.3);

        Animal a4 = new Animal();
        a4.setPetName("Saul");
        a4.setName("Frank");
        a4.setBread("Wolfdog");
        a4.setColor("white with black");
        a4.setWeight(20.8);
        a4.setHeight(3.6);
        a4.setLength(6.7);


        animalRepository.save(a1);
        animalRepository.save(a2);
        animalRepository.save(a3);
        animalRepository.save(a4);



        // Students

        Student s1 = new Student();
        s1.setStudentCode("0978457891");
        s1.setName("Sol");
        s1.setLastname("Paéz");

        Student s2 = new Student();
        s1.setStudentCode("0958794859");
        s1.setName("Angel");
        s1.setLastname("Sarmiento");

        studentRepository.save(s1);
        studentRepository.save(s2);



        //Book

        Author au1 = new Author();
        au1.setName("Alejandro");
        au1.setLastname("Dumas");
        authorRepository.save(au1);

        Book b1 = new Book();
        b1.setTitle("El libro sin nombre ");
        b1.setEditorial("Dumas");
        b1.setAuthor(au1);
        bookRepository.save(b1);

        au1.getBooks().add(b1);
        authorRepository.save(au1);

        Author au2 = new Author();
        au2.setName("Luis ");
        au2.setLastname("Alvarez");
        authorRepository.save(au2);

        Book b2 = new Book();
        b2.setTitle("Crimen del castigo");
        b2.setEditorial("Alvarez");
        b2.setAuthor(au2);
        bookRepository.save(b2);

        au2.getBooks().add(b2);
        authorRepository.save(au2);


        Author au3 = new Author();
        au3.setName("Maria");
        au3.setLastname("Gonzales");
        authorRepository.save(au3);


        Book b3 = new Book();
        b3.setTitle("Asi empezo todo");
        b3.setEditorial("Gonzalez ");
        b3.setAuthor(au1);
        bookRepository.save(b3);

        au1.getBooks().add(b1);
        au1.getBooks().add(b3);
        authorRepository.save(au1);


        Author au4= new Author();
        au4.setName("Laura ");
        au4.setLastname("Ortiz");
        authorRepository.save(au4);

        Book b4 = new Book();
        b4.setTitle("Donde el corazon te lleve");
        b4.setEditorial("Ortiz");
        b4.setAuthor(au1);
        bookRepository.save(b4);

        au4.getBooks().add(b4);
        authorRepository.save(au4);


        public void createClients(){
            Client.client c1 = new Client("c-00001", "0932467913", "Wendy", "Ortiz", age 18);
            Client.client c2= new Client("c-00002", "0982765490" , "Pedro", "Rivera", age 20);
            clientRepository.save(c1);
            clientRepository.save(c2);
        }
        @Override
        public void run (String...... args) throws{
            createPeople();
            createAnimals();
            createBooksAndAuthors();
            createClients();


        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " +personRepository.count());
        System.out.println("Number of animal: " +animalRepository.count());
        System.out.println("Number of Student: " +studentRepository.count());
        System.out.println("Number of author: " + authorRepository.count());
        System.out.println("Number of book: " +   bookRepository.count());
        System.out.println(" Number of client:" + clientRepository.count());


    }

}


