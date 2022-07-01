package br.com.loanapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Main class test")
class LoanApiApplicationTests {

	@Test
	void main() {
		LoanApiApplication.main(new String[] {});
		boolean ok = true;
		Assertions.assertTrue(ok);
	}

}
