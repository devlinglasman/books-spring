package group1.googlebooks.api;

import group1.googlebooks.VolumesFixtures;
import group1.googlebooks.service.GoogleBooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TestResultsController {
    @InjectMocks
    ResultsController resultsController;
    @Mock
    GoogleBooksService googleBooksService;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(this.resultsController).build();
    }

    @Test
    public void itReturnsStatusOf200() throws Exception {
        Mockito.when(googleBooksService.get("World", 5)).thenReturn(VolumesFixtures.getCollectionOne());

        mvc.perform(MockMvcRequestBuilders
                .get("/results?searchterm=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itFormatsJsonResults() throws Exception {
        Mockito.when(googleBooksService.get("World", 5)).thenReturn(VolumesFixtures.getCollectionOne());

        mvc.perform(MockMvcRequestBuilders
                .get("/results/json?searchterm=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.kind").value("books#volumes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].id").exists());
    }
}
