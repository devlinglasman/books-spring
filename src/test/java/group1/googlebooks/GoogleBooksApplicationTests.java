package group1.googlebooks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
// This means automatically add an instance of MOckMVC to the spring dependency context
class GoogleBooksApplicationTests {

    @Autowired
    // pulling the instance from the dependency context of the MockMVC
    MockMvc thing;

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

}
