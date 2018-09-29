package com.example.ct2.track;

import com.example.ct2.busstop.Busstop;
import com.example.ct2.busstop.BusstopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tracks")
@AllArgsConstructor
public class TrackController {

    private TrackRepository trackRepository;

    @ModelAttribute("tracks")
    private List<Track> tracks() {
        return trackRepository.findAll();
    }

    @GetMapping("/")
    public String show() {
        return "track/show";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Track track = new Track();
        model.addAttribute(track);

        return "track/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable long id, Model model) {
        Track track = trackRepository.findOne(id);
        model.addAttribute(track);

        return "track/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Track track = trackRepository.findOne(id);
        model.addAttribute(track);

        return "track/form";
    }

    @PostMapping("/save")
    public String save(@Valid Track track, BindingResult bindingResult, @RequestParam String submit) {
        if ("save".equals(submit)) {
            if (bindingResult.hasErrors()) {
                return "track/form";
            }
            trackRepository.save(track);
        }
        return "redirect:/tracks/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "track/confirm";
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id, @RequestParam String submit) {
        if ("delete".equals(submit)) {
            trackRepository.delete(id);
        }
        return "redirect:/tracks/";
    }
}
