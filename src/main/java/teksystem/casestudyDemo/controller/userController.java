package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystem.casestudyDemo.database.DAO.UserDAO;
import teksystem.casestudyDemo.database.entity.User;
import teksystem.casestudyDemo.formbean.RegisterFormBean;

@Slf4j
@Controller
public class userController {

    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        return response;
    }

    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(RegisterFormBean bean) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");
        log.info(bean.toString());

        
        kjdnfkjewbfkjwe;
        for (:
             ) {
            
        }

        fdkjgtkjlrebglkjbreg
                ksdjgb

        User user = new User();
        user.setEmail(bean.getEmail());
        user.setFirstName(bean.getFirstName());
        user.setLastName(bean.getLastName());
        user.setPassword(bean.getPassword());

        userDAO.save(user);

        return response;
    }



}
