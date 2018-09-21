package com.example.ct2.bus;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/buses")
@AllArgsConstructor
public class BusController {

    private BusRepository busRepository;

    @ModelAttribute("buses")
    private List<Bus> buses() {
        return busRepository.findAll();
    }

    @GetMapping("/")
    public String show() {
        return "bus/show";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Bus bus = new Bus();
        model.addAttribute(bus);

        return "bus/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable long id, Model model) {
        Bus bus = busRepository.findOne(id);
        model.addAttribute(bus);

        return "bus/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Bus bus = busRepository.findOne(id);
        model.addAttribute(bus);

        return "bus/form";
    }

    @PostMapping("/save")
    public String save(@Valid Bus bus, BindingResult bindingResult, @RequestParam String submit) {
        if ("save".equals(submit)) {
            if (bindingResult.hasErrors()) {
                return "bus/form";
            }
            busRepository.save(bus);
        }
        return "redirect:/buses/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
//        busRepository.delete(id);
        return "bus/confirm";
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id, @RequestParam String submit) {
        if ("delete".equals(submit)) {
            busRepository.delete(id);
        }
        return "redirect:/buses/";
    }
}
