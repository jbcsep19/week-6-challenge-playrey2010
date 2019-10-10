package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }


    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processCategory")
    public String processCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/processCar")
    public String processCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/";
    }

    // form for category, form for car, update edit delete for cars
}
