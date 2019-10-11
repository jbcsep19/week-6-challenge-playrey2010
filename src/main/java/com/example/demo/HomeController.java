package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // form for category, form for car

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

    // update detail delete for cars, detail only for category
    @RequestMapping("/detail/{id}")
    public String showCategory(@PathVariable("id") long id, Model model) {
        // this is for an additional criteria
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "showcategory";
    }

    @RequestMapping("/cardetail/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/carupdate/{id}")
    public String updateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/cardelete/{id}")
    public String delCar(@PathVariable("id") long id) {
        carRepository.deleteById(id);
        // try return index if all fails
        return "index";
    }
}
