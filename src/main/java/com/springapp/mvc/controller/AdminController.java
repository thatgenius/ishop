package com.springapp.mvc.controller;

import com.springapp.mvc.DAO.AdditiveDAO;
import com.springapp.mvc.DAO.CoffeeDAO;
import com.springapp.mvc.entity.Additive;
import com.springapp.mvc.entity.Coffee;
import com.springapp.mvc.entity.CoffeeCreateForm;
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

    @Autowired
    private AdditiveDAO additiveDAO;

    @RequestMapping(value = "/")
    public String list(ModelMap model) {
        List<Coffee> resultList = coffeeDAO.findAll();
        model.addAttribute("resultList", resultList);
        return "admin/list";
    }

    @RequestMapping(value = "create")
    public String createCoffeePage(ModelMap model) {
        model.put("coffeeCreateForm", new CoffeeCreateForm());
        model.put("coffeeTypeList", getCoffeeTypeList());
        model.put("additiveTypeList", getAdditiveTypeList());
        return "admin/create";
    }

    @RequestMapping(value = "/createNewCoffee", method = RequestMethod.POST)
    public String createCoffee(@ModelAttribute("coffeeCreateForm") CoffeeCreateForm coffeeCreateForm) {

        Coffee coffee = new Coffee(0, coffeeCreateForm.getCoffeeType(), coffeeCreateForm.getDescription(), coffeeCreateForm.getPrice());
        Additive additive = additiveDAO.getByName(coffeeCreateForm.getAdditiveType());
        coffee.getAdditives().add(additive);

        coffee.getAdditives().add(additive);
        additive.getCoffees().add(coffee);

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

    public List getAdditiveTypeList() {
        List<String> list = new ArrayList<String>();
        list.add("Eggshell");
        list.add("Butter");
        list.add("Egg Whites");
        return list;
    }
}
