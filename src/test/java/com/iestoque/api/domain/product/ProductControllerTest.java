package com.iestoque.api.domain.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ProductsRegisterDTO> productsRegisterDTOJson;

    @Autowired
    private JacksonTester<ProductGetDTO> productGetDTOJson;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    ProductsRepository productsInterface;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Must return http 400 when infos are invalids")
    @WithMockUser
    void registerProduct() throws Exception {
        var response = mvc.perform(post("/products/id"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Must return http 200 when infos are valids")
    @WithMockUser
    void registerProductScenario2() throws Exception {

        var idLong = 6L;
        var id = 6;

        User simulatedUser = new User("6", "Douglas701", "teste@teste.com");
        when(userRepository.findById(idLong)).thenReturn(Optional.of(simulatedUser));

        ProductsRegisterDTO productDTO = new ProductsRegisterDTO("Feijao", "Padin", "C2", "AXD559GTR", "2023-10-09", "2022-10-09", "FOOD");

        ProductsJPA productJPA = new ProductsJPA(productDTO);

        productJPA.setUser(simulatedUser);

        when(productsInterface.save(any(ProductsJPA.class))).thenReturn(productJPA);


        var response = mvc
                .perform(
                        post("/products/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productsRegisterDTOJson.write(
                                        new ProductsRegisterDTO("Feijao", "Padin", "C2", "AXD559GTR", "2023-10-09", "2022-10-09", "FOOD")
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedProduct = new ProductGetDTO("Feijao", "Padin", "C2", "AXD559GTR", "2023-10-09", "2022-10-09", "FOOD");
        var actualProduct = objectMapper.readValue(response.getContentAsString(), ProductGetDTO.class);

        assertThat(actualProduct).isEqualToIgnoringGivenFields(expectedProduct, "id");
    }
}
