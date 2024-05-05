//package umb.khh.edudate.controllers;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.test.web.servlet.MockMvc;
//import umb.khh.edudate.services.UserService;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void getAllUsers_ReturnsListOfUsers() throws Exception {
//        List<User> users = Arrays.asList(
//                new User(1L, "John"),
//                new User(2L, "Alice")
//        );
//
//        when(userService.getAllUsers()).thenReturn(users);
//
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("John")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].name", is("Alice")));
//    }
//
//    // Добавьте другие тесты для остальных методов контроллера UserController
//}
