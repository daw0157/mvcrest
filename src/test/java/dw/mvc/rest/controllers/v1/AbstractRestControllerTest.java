package dw.mvc.rest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

import dw.mvc.rest.exceptions.ResourceNotFoundException;

public abstract class AbstractRestControllerTest {
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new ResourceNotFoundException();
		}
	}

}
