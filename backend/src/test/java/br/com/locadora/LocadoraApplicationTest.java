package br.com.locadora;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocadoraApplicationTest {

  @Autowired private ApplicationContext applicationContext;

  @Test
  void startsWithoutDatabase() {
    assertThat(applicationContext.getBean(LocadoraApplication.class)).isNotNull();
    assertThat(applicationContext.getBeansOfType(DataSource.class)).isEmpty();
  }
}
