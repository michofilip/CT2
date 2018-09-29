package com.example.ct2.index;

import com.example.ct2.bus.Bus;
import com.example.ct2.bus.BusRepository;
import com.example.ct2.busstop.Busstop;
import com.example.ct2.busstop.BusstopRepository;
import com.example.ct2.track.Track;
import com.example.ct2.track.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class IndexController {

    private final BusRepository busRepository;
    private final BusstopRepository busstopRepository;
    private final TrackRepository trackRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/generate")
    public String generate() {
        busRepository.deleteAll();
        busstopRepository.deleteAll();
        trackRepository.deleteAll();

        for (int i = 0; i < 10; i++) {
            busRepository.save(new Bus(null, "Bus " + (i + 1)));
        }

        for (int i = 0; i < 10; i++) {
            busstopRepository.save(new Busstop(null, "Busstop" + (i + 1)));
        }

        for (int i = 0; i < 10; i++) {
            trackRepository.save(new Track(null, String.format("%03d", i + 1), "A"));
            trackRepository.save(new Track(null, String.format("%03d", i + 1), "B"));
        }

        return "index";
    }
}
