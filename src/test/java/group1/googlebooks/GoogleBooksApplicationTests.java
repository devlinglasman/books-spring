package group1.googlebooks;

import group1.googlebooks.model.VolumesCollection;
import group1.googlebooks.service.GoogleBooksService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class GoogleBooksApplicationTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GoogleBooksService booksService = new GoogleBooksService();

    @Test
    public void whenGetBooks_shouldReturnFormattedBooksResults() throws Exception {
        VolumesCollection fakeVolumes = VolumesFixtures.getCollectionOne();
        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q=harry&maxResults=5";

        Mockito.when(restTemplate.getForObject(
                url, VolumesCollection.class, "harry"
        )).thenReturn(fakeVolumes);

        VolumesCollection actual = booksService.get("harry", 5);

        Assert.assertEquals(fakeVolumes, actual);

    }

}
