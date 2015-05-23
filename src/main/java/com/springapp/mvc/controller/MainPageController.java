package com.springapp.mvc.controller;

import com.springapp.mvc.DAO.CoffeeDAO;
import com.springapp.mvc.entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
        List<Coffee> resultList = coffeeDAO.findAll();
        model.addAttribute("resultList", resultList);
        return "main";
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

}