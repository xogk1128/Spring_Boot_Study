package jpabasic.ex1hellojpa;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex1HelloJpaApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Book book = new Book();
			book.setName("JPA");
			book.setAuthor("김영한");

			em.persist(book);

			tx.commit();

		} catch (Exception e){
			tx.rollback();
		} finally {
			em.close();

			emf.close();
		}

		SpringApplication.run(Ex1HelloJpaApplication.class, args);
	}

}
