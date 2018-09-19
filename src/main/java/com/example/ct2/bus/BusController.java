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
    public String get() {
        return "bus/show";
    }

    @PostMapping("/")
    public String post(@Valid Bus bus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "bus/form";
        }
        busRepository.save(bus);
        return "redirect:/buses/";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        Bus bus = busRepository.findOne(id);
        model.addAttribute("bus", bus);

        return "bus/details";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable long id, Model model) {
        Bus bus = busRepository.findOne(id);
        model.addAttribute("bus", bus);

        return "bus/form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        busRepository.delete(id);
        return "redirect:/buses/";
    }

}
