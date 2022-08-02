package br.com.installmentmicroservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Main class test")
class InstallmentMicroServiceApplicationTests {

	@Test
	void main() {
		boolean ok = true;
		Assertions.assertTrue(ok);
	}

}
