package group1.googlebooks.service;

import group1.googlebooks.model.VolumesCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService implements BooksService {
    private final String googleApiKey = "AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0";
    private RestTemplate restTemplate;

    public GoogleBooksService() {
        this.restTemplate = new RestTemplate();
    }

    public VolumesCollection get(String searchTerm, int maxResults) {
        String url = "https://www.googleapis.com/books/v1/volumes?key=" +
                this.googleApiKey +
                "&q=" +
                searchTerm +
                "&maxResults=" +
                maxResults;

        return this.restTemplate.getForObject(url, VolumesCollection.class, searchTerm);
    }
}
