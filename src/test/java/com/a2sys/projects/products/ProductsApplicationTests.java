package com.a2sys.projects.products;

import com.a2sys.projects.products.model.Product;
import com.a2sys.projects.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void thatGetProductShouldReturnHttpStatusOK() throws Exception {
		mockMvc
				.perform(get("/api/product/lkjhfdsl"))
				.andExpect(status().isOk());
	}

	@Test
	public void thatMockRepoReturnAProduct(){
		Product product = new Product();
		product.setId("sdfsdfsdf");
		product.setName("Orange");

		given(productRepository.findById("1"))
				.willReturn(Optional.of(new Product()));


	}

}
