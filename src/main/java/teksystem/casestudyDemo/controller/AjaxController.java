package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class AjaxController {
    //Need to have a simple get for the page to load
    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public ModelAndView ajax() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("ajax");

        return response;
    }
}
