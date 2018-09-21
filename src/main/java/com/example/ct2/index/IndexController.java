package com.example.ct2.index;

import com.example.ct2.bus.Bus;
import com.example.ct2.bus.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class IndexController {

    private final BusRepository busRepository;

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("message", "Hello Coderslab");
        return "index";
    }

    @GetMapping("/generate")
    public String generate() {
        busRepository.deleteAll();

        for (int i = 0; i < 10; i++) {
            busRepository.save(new Bus(null, "Bus " + (i + 1)));
        }

        return "index";
    }
}
