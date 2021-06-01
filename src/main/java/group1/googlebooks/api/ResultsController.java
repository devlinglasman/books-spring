package group1.googlebooks.api;

import group1.googlebooks.model.VolumesCollection;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ResultsController {
    private final RestTemplate restTemplate;

    public ResultsController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/results")
    public ModelAndView booksResults(@RequestParam(value = "searchterm", required = false, defaultValue = "World") String searchterm, ModelAndView mav) {
        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB8PrVZN2nGqgHp1BJ6M3RPIuH1ME0Vaw0&q={searchterm}&maxResults=5";
        VolumesCollection volumes = this.restTemplate.getForObject(url, VolumesCollection.class, searchterm);
        mav.setViewName("results");
        mav.addObject("volumes", volumes.items);
        return mav;
    }
}
