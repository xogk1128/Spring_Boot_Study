package jpabasic.ex1hellojpa;

import jakarta.persistence.*;
import jpabasic.ex1hellojpa.jpql.Member1;
import jpabasic.ex1hellojpa.jpql.MemberDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class Ex1HelloJpaApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member1 member = new Member1();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();

			String jpql = "select t from Team t";
			List<Team> result = em.createQuery(jpql, Team.class)
					.setFirstResult(0)
					.setMaxResults(2)
					.getResultList();



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
