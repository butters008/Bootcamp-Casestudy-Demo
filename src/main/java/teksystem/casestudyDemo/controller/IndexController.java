package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

//    @Autowired
//    private UserDAO userDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("index");
        return response;

    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public ModelAndView index() throws Exception {
//        ModelAndView response = new ModelAndView();
//
//        List<User> users = userDAO.findByFirstName("Keith");
//
//        for(User user : users){
//            log.info(user.toString());
//        }
//
//        response.setViewName("index");
//        response.addObject("userList", users);
//
//        return response;
//
//    }

}
