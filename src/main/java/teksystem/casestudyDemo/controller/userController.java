package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystem.casestudyDemo.database.DAO.UserDAO;
import teksystem.casestudyDemo.database.entity.User;
import teksystem.casestudyDemo.formbean.RegisterFormBean;

import java.util.List;

@Slf4j
@Controller
public class userController {

    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        //seeding the model fo it wont be empty
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }


    //This method becomes a create and edit method
    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(RegisterFormBean bean) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");
        log.info(bean.toString());

        /*
        * The firs thing we will try and do is see if there is a user already in the DB
        * If there is, than it will skip the if statement
        * */
        User user = userDAO.findById(bean.getId());

        // The inital check came back null, so we are going to create a new user.
        if (user == null) {
            user = new User();
        }

        user.setEmail(bean.getEmail());
        user.setFirstName(bean.getFirstName());
        user.setLastName(bean.getLastName());
        user.setPassword(bean.getPassword());

        userDAO.save(user);

        //Redirecting the user to the edit page
        //The edit page will be responsable for loading the user from the DB
        //Edit page will take care of RU of CRUD
        //When you use redirect: as part of the view name, it triggers spring to tell the
        //browser to o a redirect the url after the :
        response.setViewName("redirect:/user/edit/" + user.getId());


        return response;
    }


//    @RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
    @GetMapping(value = "/user/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        User user = userDAO.findById(userId);

        RegisterFormBean form = new RegisterFormBean();

        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setPassword(user.getPassword());
        form.setPasswordConfirm(user.getPassword());

        response.addObject("form",form);

        return response;
    }

    @GetMapping(value = "/user/search")
    public ModelAndView searchUser() throws Exception {
        //The ModelAndView is the class that says hey, we need to get the model
        //which the model send this to the view.
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        //This is just getting a list of users with the first name of Keith
        //We need to do it dynamically, but this way works for example -- just hard code it
        List<User> users = userDAO.findByFirstName("Keith");
        response.addObject("users", users);

        return response;
    }


}
