package com.big.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


//@Entity는 자바 클래스를 JPA가 관리하는 엔티티로 인식하게 하는 어노테이션, JPA는 이 클래스로부터 생성된 객체를 엔티티로 인식한다.
@Entity  //스프링프레임워크에서 JPA를 사용한다면 @Entity붙은 엔티티들을 자동으로 스캔하여 처리하기 때문에 엔티티클래스를 명시적으로 등록하지 않아도 된다. 하지만 JPA를 단독으로 사용하는 경우 엔티티를 클래스들을 영속성 유닛에 등록해야 한다.
//@Table(name = "BOARD")  //@Entity가 추가된 Board 클래스는 클래스 이름에 해당하는  BOARD 테이블과 자동으로 매핑된다. 만약 Board 클래스와 다른 테이블을 매핑하려면 반드시 @Table을 사용해야 한다.
                        //엔티티 이름과 테이블 이름이 다른 경우에 @Table을 이용하여 매핑할 테이블 이름을 정확히 지정해야 한다.  name 매핑할 테이블 이름, catalog 데이터베이스 카탈로그(데이터베이스 카탈로그는 데이터베이스의 개체들에 대한 정의를 담고 있는 메타데이터들로 구성된 데이터베이스 내의 인스턴스이다. 기본 테이블, 뷰 테이블, 동의어들, 값 범위들, 인덱스들, 사용자들, 사용자 그룹 등등과 같은 데이터베이스의 개체들이 저장된다.)
						//schema 데이터베이스 스키마
                       //uniqueConstraints 결합 unique 제약 조건 지정하며, 여러 개의 칼럼이 결합되어 유일성을 보장해야 하는 경우 사용

@TableGenerator(name="BOARD_SEQ_GENERATOR", table = "ALL_SEQUENCES", pkColumnValue = "BOARD_SEQ",initialValue = 0,allocationSize = 1) //pkColumnValue : "BOARD_SEQ" 이름으로 증가되는 값을 저장하라
public class Board {
	
	
	// 엔티티로부터 생성된 객체는 반드시 다른 객체와 식별할 수 있어야 하는데 이를 위해서 반드시 사용해야 하는 어노테이션이 @ID
	// 테이블에 저장된 각 로우는 PK(Primary Key) 칼럼을 통해 유일한 데이터로 식별할 수 있다. 그리고 이런 테이블과 매핑되는 엔티티 역시 PK 칼럼과 매핑될 식별자를 가지고 있어야 하는데 이를 식별자 필드라고 한다. JPA는 @Id를 이용해서 식별자 필드를 매핑한다.
	@Id     //테이블의 기본키(primary key), @Id가 없는 엔티티는 사용하지 못함
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")  //@Id가 선언된 필드에 기본 키 값을 자동으로 할당 strategy : 자동생성 전략을 선택
	                 //TABLE, SEQUENCE,IDENTITY(auto_increment나 IDENTITY를 이용하여 PK값을 생성,일반적으로 MySQL 이용시 사용),AUTO(기본값)
	private Long seq;
	
	
//	@Transient    // 엔티티 클래스의 변수들을 대부분 테이블의 칼럼과 매핑된다. 그러나 몇몇 변수는 매핑되는 칼러밍 없거나 검색 관련 변수 값이 임시로 사용되는 변수들은 아예 매핑에서 제외해야 하는 경우
//	private String searchCondition;
//	@Transient    
//	private String searchKeyword;
	
//@Column(name = "tt")
//@Column : 엔티티의 변수와 테이블을 칼럼을 매핑할 때 사용, 일반적으로 엔티티의 변수 이름과 칼럼 이름이 다를 때 사용, 생략하면 기본으로 변수이름과 동일한 이름의 칼럼이 매핑
//name : 생략시 프로퍼티명과 동일하게 매핑, unique : unique 제약 조건 추가(기본값:false), nullable : null  상태 허용여부(false)
//insertable : INSERT를 생성할 때 이 칼럼을 포함할 것인지 결정(true), updatable :UPDATE를 생성할 때 이 칼럼을 포함할 것인지 결정(true)
//columnDefinition : 이 칼럼에 대한 DDL 문을 직접 기술 , length : 문자열 타입의 칼럼 길이 지정(255)
//precesion : 숫자 타입의 전체 자릴수 지정(0), scale : 숫자 타입의 소수점 자릿수 지정(0) 
	
	
	private String title;
	private String writer;
	private String content;
	
	
	
//@Temporal java.util.Date 타입의 날짜 데이터를 매핑할 때 사용
	@Temporal(value = TemporalType.DATE)
	private Date createDate;
	private Long cnt;
	
	
	

	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}
	
	
}
