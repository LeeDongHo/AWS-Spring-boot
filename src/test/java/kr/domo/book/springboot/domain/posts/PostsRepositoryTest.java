package kr.domo.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)

// 별다른 설정 없이 아래 어노테이션을 사용할 경우 H2 데이터베이스를 자동으롯 ㅣㄹ행
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // @After -> @AfterEach로 변경
    // Junit에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정
    // 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용하며,
    // 여러 테스트가 동시에 수행되면 H2 (테이트용 DB)에 데이터가 그대로 남아있어 다음 테스트 실패할 수 있습니다.
    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update 쿼리를 실행하며, id값이 있으면 update 없으면 insert
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("edh1021@gmail.com")
                .build());

        // when
        // posts 테이블의 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
