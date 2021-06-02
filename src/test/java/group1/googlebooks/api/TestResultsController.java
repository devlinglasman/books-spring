package group1.googlebooks.api;

import group1.googlebooks.VolumesFixtures;
import group1.googlebooks.service.GoogleBooksService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestResultsController {
    @InjectMocks
    ResultsController resultsController;
    @Mock
    GoogleBooksService googleBooksService;
    @Autowired
    private MockMvc mvc;

    @Before
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
