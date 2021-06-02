package group1.googlebooks.api;

import group1.googlebooks.model.Volume;
import group1.googlebooks.model.VolumeInfo;
import group1.googlebooks.model.VolumesCollection;
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
        Volume fakeVolume1 = new Volume();
        fakeVolume1.id = "fake-id-1";
        fakeVolume1.kind = "books#volume";
        VolumeInfo fakeVolume1Info = new VolumeInfo();
        fakeVolume1Info.title = "Book 1";
        fakeVolume1Info.authors = new String[]{"Mary Shelley", "Gertrude"};
        fakeVolume1.volumeInfo = fakeVolume1Info;

        Volume fakeVolume2 = new Volume();
        fakeVolume2.id = "fake-id-2";
        fakeVolume2.kind = "books#volume";
        VolumeInfo fakeVolume2Info = new VolumeInfo();
        fakeVolume2Info.title = "Book 2";
        fakeVolume2Info.authors = new String[]{"Ian McEwan", "Rivers Solomon"};
        fakeVolume2.volumeInfo = fakeVolume2Info;

        VolumesCollection fakeVolumes = new VolumesCollection();
        fakeVolumes.totalItems = 18;
        fakeVolumes.kind = "books#volumes";
        fakeVolumes.items = new Volume[]{
                fakeVolume1,
                fakeVolume2
        };

        Mockito.when(googleBooksService.get("World", 5)).thenReturn(fakeVolumes);

        mvc.perform(MockMvcRequestBuilders
                .get("/results?searchterm=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itFormatsJsonResults() throws Exception {
        Volume fakeVolume1 = new Volume();
        fakeVolume1.id = "fake-id-1";
        fakeVolume1.kind = "books#volume";
        VolumeInfo fakeVolume1Info = new VolumeInfo();
        fakeVolume1Info.title = "Book 1";
        fakeVolume1Info.authors = new String[]{"Mary Shelley", "Gertrude"};
        fakeVolume1.volumeInfo = fakeVolume1Info;

        Volume fakeVolume2 = new Volume();
        fakeVolume2.id = "fake-id-2";
        fakeVolume2.kind = "books#volume";
        VolumeInfo fakeVolume2Info = new VolumeInfo();
        fakeVolume2Info.title = "Book 2";
        fakeVolume2Info.authors = new String[]{"Ian McEwan", "Rivers Solomon"};
        fakeVolume2.volumeInfo = fakeVolume2Info;

        VolumesCollection fakeVolumes = new VolumesCollection();
        fakeVolumes.totalItems = 18;
        fakeVolumes.kind = "books#volumes";
        fakeVolumes.items = new Volume[]{
                fakeVolume1,
                fakeVolume2
        };

        Mockito.when(googleBooksService.get("World", 5)).thenReturn(fakeVolumes);

        mvc.perform(MockMvcRequestBuilders
                .get("/results/json?searchterm=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.kind").value("books#volumes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[*].id").exists());
    }
}
