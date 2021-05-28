package group1.googlebooks;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GetBookController {

    private final RestTemplate restTemplate;

    public GetBookController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/greeting")
    public String getBook(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
//    public String getBook() {
        return "greeting";
//        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q={name}&maxResults=5";
//        return this.restTemplate.getForObject(url, VolumesCollection.class, name);
    }
}