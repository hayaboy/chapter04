
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.big.domain.Board;

public class JPAClient {
	
	public static void main(String[] args) {
		
		//영속성 유닛을 설정하면 애플리케이션에서는 영속성 유닛 설정을 로딩해서 EntityManagerFactory를 생성할 수 있다.
		//애플리케이션에서 JPA를 이용하기 위해서는 EntityManager 객체가 필요한데, EntityManagersms EntityManagerFactory로부터 얻어낸다.
		// EntityManager 생성     
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		EntityManager em = emf.createEntityManager();

		
		//Transaction 생성
		EntityTransaction tx=em.getTransaction();
		
		try {
			
			//Transaction 시작
			tx.begin();
			
			
			Board board=new Board();
			
			board.setTitle("제목3");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 연습3");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			//글 등록
			em.persist(board);
			
			
			//Transaction commit
			tx.commit();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		

	}

}
