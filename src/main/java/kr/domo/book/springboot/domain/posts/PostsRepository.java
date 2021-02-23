package kr.domo.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts  p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*
    Repository : MyBatis, ibatis에서 Dao라고 불리는 DB Layer 접근자
        -> 인터에피스로 생성하며, JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적 CRUD 메소드 자동 생성
        @Repository 추가 할 필요 없음. Entiry 클래스와 기본 Entity Repository는 함께 위치해야한다.
            -> 도메인별로 프로젝트 분리시 도메인 패키지에서 함께 관리

*/