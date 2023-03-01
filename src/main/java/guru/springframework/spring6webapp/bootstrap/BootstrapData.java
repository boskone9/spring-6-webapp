package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher bandNPubs = new Publisher();
        bandNPubs.setPublisherName("Barnes and Noble");
        Publisher bandNPubsSaved = publisherRepository.save(bandNPubs);

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        Author ericSaved = authorRepository.save(eric);

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");
        ddd.setPublisher(bandNPubsSaved);
        Book dddSaved = bookRepository.save(ddd);

        ericSaved.getBooks().add(dddSaved);
        authorRepository.save(ericSaved);
        dddSaved.getAuthors().add(ericSaved);
        bookRepository.save(dddSaved);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
        Author rodSaved = authorRepository.save(rod);

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development Without EJB");
        noEJB.setIsbn("7890123");
        noEJB.setPublisher(bandNPubsSaved);
        Book noEJBSaved = bookRepository.save(noEJB);

        rodSaved.getBooks().add(noEJBSaved);
        authorRepository.save(rodSaved);
        noEJBSaved.getAuthors().add(rodSaved);
        bookRepository.save(noEJBSaved);

        // Report on repositories

        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
