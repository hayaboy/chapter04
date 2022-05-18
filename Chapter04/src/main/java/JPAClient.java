
import java.util.Date;
import java.util.List;

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

		
		//Transaction 생성(JPA가 실제 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 해당 작업이 반드시 트랜잭션 안에서 수행되어야 한다.)
		EntityTransaction tx=em.getTransaction();
		
		try {
			
			//Transaction 시작
			tx.begin();
			
			
			Board board=new Board();
			
			board.setTitle("제목4");
			board.setWriter("관리자");
			board.setContent("JPA 글4 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(2L);
			
			em.persist(board);
			
			Board board2=new Board();
			board2.setTitle("제목5");
			board2.setWriter("관리자");
			board2.setContent("JPA 글5 등록 잘 되네요");
			board2.setCreateDate(new Date());
			board2.setCnt(3L);
			
			//글 등록
			em.persist(board2);  //persist()메소드를 이용하여 Board 엔티티에 설정된 값을 BOARD 테이블에 저장
			
			
			
			//수정할 게시글 조회
			
			//Board board=em.find(Board.class, 1L);
			//board.setTitle("수정된 제목");
			
			
			
			//삭제할 게시글 조회
//			Board board1=em.find(Board.class, 1L);
//			board1.setSeq(1L);
			
			//게시글 삭제			
//			board1.setSeq(1L);
//			em.remove(board1);
		

			//글 목록 조회
			String jpql="select b from Board b order by b.seq desc";
			
			List<Board> boardList=em.createQuery(jpql, Board.class).getResultList();
			for(Board i : boardList) {
				System.out.println(i.toString());
			}
			
			//persist(Object entity) 엔티티를 영속화한다(INSERT).
			//merge(Object entity) 준영속상태의 엔티티를 영속화한다.(UPDATE)
			//remove(Object entity) 영속상태의 엔티티를 제거ㅏ다.(DELETE)
			//find(Class<T> entityClass, Object primaryKey) 하나의 엔티티를 검색한다.(SELECT ONE)
			//createQuery(String jpql, Class<T> resultClass) JPQL에 해당하는 엔티티 목록 검색(SELECt LIST)
			
			
			
			
			
			//글 조회
//			Board boardResult=em.find(Board.class, 3L);
//			System.out.println(boardResult.toString());
			
			
			//Transaction commit
			tx.commit();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			//Transaction rollback
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
		

	}

}
