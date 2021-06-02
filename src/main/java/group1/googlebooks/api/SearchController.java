package group1.googlebooks.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SearchController {

    @GetMapping("/search")
    public ModelAndView searchBooks(ModelAndView mav) {
        mav.setViewName("search");
        return mav;
    }
}