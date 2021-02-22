package kr.domo.book.springboot.domain.posts;

import kr.domo.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 저자는 필수 어노테이션일수록 클래스에 가깝게 배치했다. (Getter, NoargsConstructor : lombok / Entity : JPA)
//  -> JPA가 더 필수이므로 (코틀린 등 새 언어 전환 때 롬복 제거) 클래스에 더 가깝게 배치
@Getter
// 기본 생성자 자동 추가 -> publi Posts() {}와 같은 효과
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타내며 클래스 네이밍은 카멜표기법, 테이블 네이밍은 _표기법을 쓴다.
@Entity
// 실제 DB 테이블과 매칭될 클래스, Entity 클래스라고도 부른다.
public class Posts extends BaseTimeEntity {
    // 해당 테이블의 PK 필드를 나타낸다.
    @Id
    // PK의 생성 규칙 정의, 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야지 auto_increment 적용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블 컬럼을 나타내며 굳이 선언 안하더라도 해당 클래스 필드는 모드 컬럼이 된다.
    // 기본값 외 추가로 변경이 필요할 때 사용한다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에서 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/*
    Entity의 PK를 Long 타입의 Auto_increment를 추천!
    주민등록번호나 복합키를 PK로 잡을경우 아래 문제 발생
        1. FK 맺을 때 다른 테이블에서 복합키를 전부 갖고있거나, 중간 테이블을 하나 더 둬야하는 상황 발생
        2. 인덱스에 안좋은 영향
        3. 유니크 조건이 변경될 경우 PK 전체 조건 변경
        -> 주민번호, 복합키 등은 유니크 키로 별도 추가하는 것 추천
*/

/*
    자바빈 규약을 생각해서 Getter/Setter를 무작정 생성하는 경우가 있다.
    Entity클래스에서는 절대 Setter 메소드를 만들지 않는다.
    해당 값 추가, 변경이 필요하면 명확히 그 목적과 의도를 알 수 있는 메소드를 추가해야한다.
    기본적으로 생성자를 통해 최종값을 채운 후 DB에 Insert
*/