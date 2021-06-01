package group1.googlebooks.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestResultsController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void itReturnsStatusOf200() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/results")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }

    @Test
    public void itReturnsStatusOf200ForJson() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/results/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itFormatsJsonResults() throws Exception {
        // Not a great test because it touches Google's API
        // Need to figure out how to leverage the BooksService
        mvc.perform( MockMvcRequestBuilders
                .get("/results/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.kind").value("books#volumes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].id").exists());
    }
}
