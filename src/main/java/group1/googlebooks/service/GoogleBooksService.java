package group1.googlebooks.service;

import group1.googlebooks.model.VolumesCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService implements BooksService {
    private RestTemplate restTemplate;
    private String googleApiKey = "AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0";

    public GoogleBooksService() {
        this.restTemplate = new RestTemplate();
    }

    public VolumesCollection get(String searchTerm, int maxResults) {
        StringBuilder builder = new StringBuilder();
        String url = builder
                .append("https://www.googleapis.com/books/v1/volumes?key=")
                .append(this.googleApiKey)
                .append("&q=")
                .append(searchTerm)
                .append("&maxResults=")
                .append(maxResults)
                .toString();

        return this.restTemplate.getForObject(url, VolumesCollection.class, searchTerm);
    }
}
