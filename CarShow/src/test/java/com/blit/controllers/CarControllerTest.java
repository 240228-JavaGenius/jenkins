package com.blit.controllers;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blit.entity.Car;
import com.blit.service.CarService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class CarControllerTest {
	
	@Autowired //MockMvc Allows me to test actual responses. 
	private MockMvc mockMvc;

	@Mock //mocked dependency
	private CarService carService;
	
	@InjectMocks //Class under test that will take the mocks
	private CarController carController;
	
//	@BeforeEach
//	void setUpMocks() {
//		//static import to Mockito for the "when" method
//		when(carService.getCarById(1L)).thenReturn
//			(new Car(1L, "Ford", "F-350", "white", 
//					"123dfgt2", 2010, 12345));
//	}
	
	@Test
	public void getCarShouldReturnOKIfExists() {
		
		when(carService.getCarById(1L)).thenReturn
		(new Car(1L, "Ford", "F-350", "white", 
				"123dfgt2", 2010, 12345));
		ResponseEntity<Car> respEntity = carController.getCar(1L);
		assertEquals(HttpStatusCode.valueOf(200), respEntity.getStatusCode());
		//Check the body has the right object.
		assertEquals("F-350", respEntity.getBody().getModel());
	}
	
	@Test
	public void getAllCarsShouldReturnArrayAndStatusOk() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
		when(carService.getCars()).thenReturn(new ArrayList<Car>());
		
		HttpHeaders head = new HttpHeaders();
		head.setBasicAuth("admin", "adminPass"); //set up basic auth
		mockMvc.perform(get("/api/v1/car/all").headers(head)) //sends GET request
			.andDo(print()) //See the req and resp in console;
			.andExpect(status().isOk())
			.andExpect(content().string(startsWith("[")));
	}
	
	@Test
	public void deleteCarNoAuthReturns401() throws Exception {
		//TODO ensure mockMvc is using mock service
		// mocked void methods do nothing by default. 
		mockMvc.perform(delete("/api/v1/car/1"))
			.andExpect(status().isUnauthorized());
	}
	
}





