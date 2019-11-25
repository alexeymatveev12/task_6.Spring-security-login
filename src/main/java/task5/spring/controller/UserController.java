package task5.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task5.spring.model.User;
import task5.spring.service.UserService;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Deprecated
    //setter - deprecated
//    @Autowired
//    public void setUserService(UserService userService) {
//
//        this.userService = userService;
//    }

    /*//GET - page Read list of users
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(Model model) {
  //      model.addAttribute("allUsers", userService.getAllUsers());
        return "welcome";
    }*/

    //GET - page Read list of users
    @RequestMapping(value = "/admin/read", method = RequestMethod.GET)
    public String readUserList2(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/read";
    }

    //GET - page Create
    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("create") User user, Model model) {
        model.addAttribute("user", new User());
        return "admin/create";
    }


    //POST - page Create - create the user
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("create") User user) {

        userService.addUser(user);
        return "redirect:/";
    }
/////////////////////////////////////////////

    //GET - обновляем страница обновления
    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    //POST - обновляем юзера
    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("allUsers", userService.getAllUsers());// ? а зачем - в риде должно быть
        // return "redirect:/read";
        return "admin/read";
    }

    //GET - delete page - confirmation
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/delete";
    }

    //Post- delete
    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") User user, Model model) {
        userService.deleteUserById(user.getId());
        model.addAttribute("allUsers", userService.getAllUsers()); // ? а зачем - в риде должно быть
        //  return "redirect:/read";
        return "admin/read";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

}


