package jpabasic.ex1hellojpa;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex1HelloJpaApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(team);

			em.persist(member1);

			em.flush();
			em.clear();

			List<Member> members = em.createQuery("select m from Member m", Member.class)
					.getResultList();

//			Member m = em.find(Member.class, member1.getId());

//			System.out.println("m = " + m.getTeam().getClass());

//			m.getTeam().getName();

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
