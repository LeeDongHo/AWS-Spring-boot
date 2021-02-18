package kr.domo.book.springboot.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

/*
    JUnit의 assertThat이 아닌 assertj의 assertThat를 사용.
    assertj를 사용하면서 얻은 장점

    1. CoreMatchers와 달리 추가적으로 라이브러리 필요하지 않는다.
    2. 자동완성이 확실하게 지원됨.
*/