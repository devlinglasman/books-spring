package group1.googlebooks;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ResultsController {
    private final RestTemplate restTemplate;

    public ResultsController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/results")
    public String booksResults(@RequestParam(value = "searchterm", required = false, defaultValue = "World") String searchterm, Model model) {
        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q={searchterm}&maxResults=5";
        VolumesCollection volumes = this.restTemplate.getForObject(url, VolumesCollection.class, searchterm);
        model.addAttribute("volumes", volumes.items);
        return "results";
    }
}
