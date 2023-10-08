package com.enyziee.rinhabackend;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
class RinhabackendApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void insertSameApelido() {
		Pessoas pessoa1 = new Pessoas("test1", "test1", "2000-10-01", null);
		ResponseEntity<Void> response1 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);
		ResponseEntity<Void> response2 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);

		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}


	@Test
	void validPostTest() throws IOException {
		Pessoas pessoa1 = new Pessoas("josé", "José Roberto", "2000-10-01", new String[] { "C#", "Node", "Oracle" });
		Pessoas pessoa2 = new Pessoas("ana", "Ana Barbosa", "1985-09-23", null);

		ResponseEntity<Void> response1 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		ResponseEntity<Void> response2 = restTemplate.postForEntity("/pessoas", pessoa2, Void.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<String> response3 = restTemplate.getForEntity(response1.getHeaders().getLocation(), String.class);
		assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);

		ResponseEntity<String> response4 = restTemplate.getForEntity(response2.getHeaders().getLocation(), String.class);
		assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void invalidPostTest() throws IOException {
		// caso "zé" já tenha sido criado em outra requisição
		Pessoas pessoa1 = new Pessoas("zé", "José Roberto", "2000-10-01", new String[] { "C#", "Node", "Oracle" });
		ResponseEntity<Void> response1 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);
		ResponseEntity<Void> response2 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

		// "nome" não pode ser null
		Pessoas pessoa2 = new Pessoas("ana", null, "1985-09-23", null);
		ResponseEntity<Void> response3 = restTemplate.postForEntity("/pessoas", pessoa2, Void.class);
		assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

		// "apelido" não pode ser null
		Pessoas pessoa3 = new Pessoas(null, "Ana Barbosa", "1985-09-23", null);
		ResponseEntity<Void> response4 = restTemplate.postForEntity("/pessoas", pessoa3, Void.class);
		assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	void syntaxInvalidPostTest() throws IOException {
		Pessoas pessoa1 = new Pessoas("apelido", "1", "1985-01-01", null);
		ResponseEntity<Void> response1 = restTemplate.postForEntity("/pessoas", pessoa1, Void.class);
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

		Pessoas pessoa2 = new Pessoas("apelido", "nome", "1985-01-01", new String[] { "1", "PHP", });
		ResponseEntity<Void> response2 = restTemplate.postForEntity("/pessoas", pessoa2, Void.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}
}
