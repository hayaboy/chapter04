import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.big.domain.Board;

public class JPAClient2 {
	
	
	public static void main(String[] args) {
		// EntityManager 생성
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

				EntityManager em = emf.createEntityManager();

				
				//Transaction 생성
				EntityTransaction tx=em.getTransaction();
				
				try {
					
					//글 상세 조회
					Board board=em.find(Board.class, 1L);
					System.out.println(board.toString());
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					em.close();
					emf.close();
				}
		
	}

}
