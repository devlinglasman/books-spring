package group1.googlebooks.api;

import group1.googlebooks.model.VolumesCollection;
import group1.googlebooks.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ResultsController {
    private int maxNumberOfResults = 5;

    @Autowired
    private BooksService booksService;

    @GetMapping("/results")
    public ModelAndView booksResults(@RequestParam(value = "searchterm", required = false, defaultValue = "World") String searchTerm, ModelAndView mav) {
        VolumesCollection volumes = booksService.get(searchTerm, this.maxNumberOfResults);
        mav.setViewName("results");
        mav.addObject("volumes", volumes.items);
        return mav;
    }

    @GetMapping("/results/json")
    public ResponseEntity<VolumesCollection> booksResultsAsJson(@RequestParam(value = "searchterm", required = false, defaultValue = "World") String searchTerm) {
        VolumesCollection volumes = booksService.get(searchTerm, this.maxNumberOfResults);
        ResponseEntity<VolumesCollection> responseEntity = new ResponseEntity(volumes, HttpStatus.OK);
        return responseEntity;
    }
}
