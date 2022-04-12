package teksystem.casestudyDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystem.casestudyDemo.database.DAO.UserDAO;
import teksystem.casestudyDemo.database.entity.User;
import teksystem.casestudyDemo.database.entity.UserRole;
import teksystem.casestudyDemo.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class userController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Need to have a simple get for the page to load
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");


        return response;
    }


    /*
     * This method is now being used to create and edit the user.
     *
     * You can do @RequestMapping() and have both GET and POST method so it can pull and push data from the DB
     * and manipulate data.
     *
     * Post = pushing data to the DB and manipulate data to put into DB
     * GET = getting info from DB and collecting DB from DB with form (look at search page for detail)
     * */
    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean bean, BindingResult bindingResult) throws Exception {
        /*
         * An understanding of what is going on and how this is using the MVC
         * The class -> ModelAndView (w/ ref name of: ) response <- is what we are using to connect the
         * model and the view to this controller.  We are saying "With this MODEL and this VIEW", connect
         * with the response.
         *
         * So, we query with the entity DAO (our example userDAO) and we take the query and put it in our model
         * which is the RegisterFormBean (which is a POJO, atm).  Once we put it in the model, Spring will than
         * say it needs to go to a view, and we use a few different methods to associate the model to the view
         * through the controller.
         *
         * Here are the methods we are using to connect the controller and model to the view
         * (1) - @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
         *  |-> This goes on top of method and tells the controller to execute statement at this URL
         *      We can execute on a different URL while being on a different URL EX: register.jsp(view) and execution on /user/edit/{userId}
         * (2) - response.setViewName("user/register");
         *  |-> This tells the controller that this statement is associated to this view
         *      We can have multiple executions on the same view
         * (3) - response.addObject("users", users);
         *  |-> Adding objects to the view. This is how we add/show the model/info on the view.
         * */
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            //Creating a new map for the errors
//            HashMap errors = new HashMap();

            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                /*
                 * The first Param -> ((FieldError) error).getField() <- is the key
                 * while the second param -> error.getDefaultMessage() <- is the value
                 * */
//                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                errorMessages.add(error.getDefaultMessage());
                //Logging out to the console
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            //Adding the list of errors to the model
//            response.addObject("formErrors", errors);
            log.info(errorMessages.toString());
            response.addObject("bindingResult", bindingResult);

            /*
             * Add the form back to the mofel so the we can fill up the input field
             * so that we correct the input and we do not have to retype it all again
             * */
            response.addObject("form", bean);

            /*
             * Because this is an error, we do not want to process the logic below that will creat a new user
             * We want to show register.jsp
             * */
            response.setViewName("user/register");
            return response;
        }//End of Error handling

        response.setViewName("user/register");
        log.info(bean.toString());

        /*
         * The first thing we will try and do is see if there is a user already in the DB
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
        String password = passwordEncoder.encode(bean.getPassword());
        user.setPassword(password);

        userDAO.save(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");
        //manually do admin roles

        /*
         * Now we are redirecting the user to the edit page, and this is where the UPDATE
         * portion of CRUD will take place. Since we have to redirect the user to the edit view
         * which is still in the register.jsp page (explanation -> look at editUser method <-)
         * */
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

        response.addObject("form", form);

        return response;
    }

    //Need to have a simple get for the page to load
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/user/search")
    public ModelAndView searchUser() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        //This is just getting a list of users with the first name of Keith
        //We need to do it dynamically, but this way works for example -- just hard code it
        List<User> users = userDAO.findByFirstName("BOB");
        response.addObject("users", users);

        return response;
    }

    /*
     * create a form on the user search page that submits to this route using a get method
     * make an input box for the user to enter a search term for first name
     * add a @RequestParam to take in a search value from the input box - use required = false in the annotation
     * use the search value in the query
     * add the search value to the model and make it display in the input box when the page reloads
     * add error checking to make sure that the incoming search value is not null and is not empty.
     * find apache string utils on maven central and add it to your pom file - very high recommendation
     * research the StringUtils.isEmpty function and use for error checking
     * */
    @GetMapping(value = "/user/searchUserName")
    public ModelAndView searchUserName(@RequestParam(value = "searchName", required = false) String name) throws Exception {
        ModelAndView response = new ModelAndView();


//        Errors errors = new BeanPropertyBindingResult(users, "users");
//        log.info(errors.toString());

        log.info(name);

        if (name != null || !name.equals("")) {
            //rings true and errors
            List<User> users = userDAO.findByFirstName(name);
            response.addObject("users", users);
            response.setViewName("user/search");

        } else {
            response.setViewName("redirect:/user/search");
        }

        return response;
    }


}
