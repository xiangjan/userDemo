package com.example.demo

import com.example.demo.dto.DTOUser
import com.example.demo.dto.UserPasswordDTO
import com.example.demo.service.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = ["server.port=8081"])
class DemoApplicationTests {

	@Autowired
	private lateinit var userService: UserService

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun testRegisterUser() {
		//회원 가입
		var usrDto = DTOUser("techfox","123")
		var url: String ="/users/sign-up"
		var json = jacksonObjectMapper().writeValueAsString(usrDto)
		var respons = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		assertThat(respons).isNotNull
		//비번 변경
		var userPasswordDTO = UserPasswordDTO("techfox","123","456")
		json = jacksonObjectMapper().writeValueAsString(userPasswordDTO)
		url ="/users/techfox/changepw"
		respons = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()
		assertThat(respons).isNotNull
		//회원삭제
		url ="/users/techfox"
		respons = mockMvc.perform(
			MockMvcRequestBuilders.delete(url)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()
	}

}
