package com.example.ct2.busstop;

import com.example.ct2.bus.Bus;
import com.example.ct2.bus.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/busstops")
@AllArgsConstructor
public class BusstopController {

    private BusstopRepository busstopRepository;

    @ModelAttribute("busstops")
    private List<Busstop> busstops() {
        return busstopRepository.findAll();
    }

    @GetMapping("/")
    public String show() {
        return "busstop/show";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Busstop busstop = new Busstop();
        model.addAttribute(busstop);

        return "busstop/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable long id, Model model) {
        Busstop busstop = busstopRepository.findOne(id);
        model.addAttribute(busstop);

        return "busstop/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Busstop busstop = busstopRepository.findOne(id);
        model.addAttribute(busstop);

        return "busstop/form";
    }

    @PostMapping("/save")
    public String save(@Valid Busstop busstop, BindingResult bindingResult, @RequestParam String submit) {
        if ("save".equals(submit)) {
            if (bindingResult.hasErrors()) {
                return "busstop/form";
            }
            busstopRepository.save(busstop);
        }
        return "redirect:/busstops/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "busstop/confirm";
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id, @RequestParam String submit) {
        if ("delete".equals(submit)) {
            busstopRepository.delete(id);
        }
        return "redirect:/busstops/";
    }
}
