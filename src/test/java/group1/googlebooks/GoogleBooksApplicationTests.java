package group1.googlebooks;

import group1.googlebooks.model.Volume;
import group1.googlebooks.model.VolumeInfo;
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


        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q=harry&maxResults=5";

        Mockito.when(restTemplate.getForObject(
                url, VolumesCollection.class, "harry"
        )).thenReturn(fakeVolumes);

        VolumesCollection actual = booksService.get("harry", 5);

        Assert.assertEquals(fakeVolumes, actual);

    }

}
