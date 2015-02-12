package com.springapp.mvc.controller;

import com.springapp.mvc.entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/sp")
public class ShoppingCartController {

    @Autowired
    private CoffeeDAO CoffeeDAO;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public String addCart2(@RequestParam("id") Integer id, HttpSession session) {
        LinkedHashMap<Coffee, Integer> map = (LinkedHashMap<Coffee, Integer>) session.getAttribute("resultMap");
        Coffee coffee = CoffeeDAO.getById(id);

        if (map == null) {
            map = new LinkedHashMap<Coffee, Integer>();
            map.put(coffee, 1);
        } else {
            int amount = 1;
            for (Coffee c : map.keySet()) {
                if (c.getId() == id) {
                    amount = map.get(c) + 1;
                    coffee = c;
                    break;
                }
            }
            map.put(coffee, amount);
        }

        List list = new ArrayList();
        list.add(getTotalAmount(map));
        list.add(getTotalPrice(map));
        session.setAttribute("itemAmount", list.get(0));
        session.setAttribute("totalPrice", list.get(1));
        session.setAttribute("resultMap", map);

        return list.get(0) + " items $" + list.get(1);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public RedirectView delete(@ModelAttribute("id") Integer id, ModelMap model, HttpSession session) {
        LinkedHashMap<Coffee, Integer> map = (LinkedHashMap<Coffee, Integer>) session.getAttribute("resultMap");
        Coffee coffee = null;

        for (Coffee c : map.keySet()) {
            if (c.getId() == id) {
                coffee = c;
                break;
            }
        }
        if (coffee != null) {
            map.remove(coffee);
        }

        session.setAttribute("resultMap", map);
        String totalPrice = map == null ? "" : String.valueOf(getTotalPrice(map));
        String itemAmount = map == null ? "" : String.valueOf(getTotalAmount(map));
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("itemAmount", itemAmount);

        RedirectView rv = new RedirectView("/sp/cart");
        rv.setExposeModelAttributes(false);
        return rv;
    }

    @RequestMapping(value = "/cart")
    public String cart(ModelMap model, HttpSession session) {
        LinkedHashMap<Coffee, Integer> map = (LinkedHashMap<Coffee, Integer>) session.getAttribute("resultMap");
        model.addAttribute("resultMap", map);
        String totalPrice = map == null ? "" : String.valueOf(getTotalPrice(map));
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    public float getTotalPrice(Map<Coffee, Integer> map) {
        float totalPrice = 0;
        for (Coffee coffee : map.keySet()) {
            totalPrice += (map.get(coffee) * coffee.getPrice());
        }
        return totalPrice;
    }

    public int getTotalAmount(Map<Coffee, Integer> map) {
        int amount = 0;
        for (Coffee coffee : map.keySet()) {
            amount += map.get(coffee);
        }
        return amount;
    }

}
