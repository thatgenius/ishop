package com.springapp.mvc.controller;

import com.springapp.mvc.entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private CoffeeDAO coffeeDAO;

    @RequestMapping(value = "/")
    public String list(ModelMap model) {
        List<Coffee> resultList = coffeeDAO.findAll();
        model.addAttribute("resultList", resultList);
        return "admin/list";
    }

    @RequestMapping(value = "create")
    public String index(ModelMap model) {
        model.put("coffee", new Coffee());
        model.put("coffeeTypeList", getCoffeeTypeList());
        return "admin/form";
    }

    @RequestMapping(value = "/createNewCoffee", method = RequestMethod.POST)
    public String createNewCoffee(@ModelAttribute("coffee") Coffee coffee) {
        coffeeDAO.save(coffee);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public RedirectView delete(@ModelAttribute("id") Integer id) {
        coffeeDAO.delete(id);
        RedirectView rv = new RedirectView("/admin/");
        rv.setExposeModelAttributes(false);
        return rv;
    }

    public List getCoffeeTypeList() {
        List<String> list = new ArrayList<String>();
        list.add("CAPPUCCINO");
        list.add("DECAFF");
        return list;
    }
}
