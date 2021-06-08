package group1.googlebooks;

import group1.googlebooks.model.VolumesCollection;
import group1.googlebooks.service.GoogleBooksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class GoogleBooksApplicationTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private final GoogleBooksService booksService = new GoogleBooksService();

    @Test
    public void whenGetBooks_shouldReturnFormattedBooksResults() throws Exception {
        VolumesCollection fakeVolumes = VolumesFixtures.getCollectionOne();
        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q=harry&maxResults=5";

        Mockito.when(restTemplate.getForObject(
                url, VolumesCollection.class, "harry"
        )).thenReturn(fakeVolumes);

        VolumesCollection actual = booksService.get("harry", 5);

        Assertions.assertEquals(fakeVolumes, actual);

    }

}
