package Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.controller.UserController;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
//	public static enum Roles {
//		ROLE_USER, ROLE_ADMIN
//	}
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	@Test
	void testGetAllUser() throws Exception {

		String uri = STARTING_URI + "/users";

		List<User> allUser = Arrays.asList(new User(1, "jack", "lam", "user", "pass",null, null));

		// when the method from service gets called, instead of actually running the
		// method, just return the allCars list
		when(service.getAllUsers()).thenReturn(allUser);

		mvc.perform(get(uri)) // perform request ..
				.andDo(print()) // ..then print request sent and response returned
				.andExpect(status().isOk()) // expect 200 status code
				.andExpect(jsonPath("$.length()").value(allUser.size())); // expected number of elements
			
		/*
		 * mvc.perfrom ( get(uri)) -> result ( HTTP response with status code and body)
		 * result object -> .andExpect() method from result object
		 * .andExpect() -> can you assert that something is true about my result
		 */

		// verify -> checks how many interactions with code there are
		verify(service, times(1)).getAllUsers(); // getAllCars() was used once
		verifyNoMoreInteractions(service); // after checking above, check service is no longer being used

	}
	
	@Test
	void testCreateUser() throws Exception {

		String uri = STARTING_URI + "/users";
		User user = new User(1, "jack", "lam", "user", "pass",null, null);

		// Mockito.any() -> have an object of the type specified, doesnt matter what
		// that object is, just expect an object to be passed
		when(service.createUser(Mockito.any(User.class))).thenReturn(user);

		// need to convert and send car object in json format
		mvc.perform(post(uri)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(user)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

	}

	
	
	
	private byte[] asJsonString(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
