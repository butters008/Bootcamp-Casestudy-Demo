package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystem.casestudyDemo.database.DAO.UserDAO;
import teksystem.casestudyDemo.database.entity.User;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(" " + authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            String currentPrincipalName = authentication.getName();
            User user = userDAO.findByEmail(currentPrincipalName);
            response.addObject("user", user);
        }

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
