package com.example.ct2.track;

import com.example.ct2.busstop.Busstop;
import com.example.ct2.busstop.BusstopRepository;
import com.example.ct2.routenode.RouteNode;
import com.example.ct2.routenode.RouteNodeRepository;
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
    private BusstopRepository busstopRepository;
    private RouteNodeRepository routeNodeRepository;

    @ModelAttribute("tracks")
    private List<Track> tracks() {
        return trackRepository.findAll();
    }

    @GetMapping("/")
    public String get() {
        return "track/show";
    }

    @PostMapping("/")
    public String post(@Valid Track track, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "track/form";
        }
        trackRepository.save(track);
        return "redirect:/tracks/";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        Track track = trackRepository.findOne(id);
        model.addAttribute("track", track);

        return "track/details";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable long id, Model model) {
        Track track = trackRepository.findOne(id);
        model.addAttribute("track", track);

        return "track/form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        trackRepository.delete(id);
        return "redirect:/tracks/";
    }

    //*****************

    @GetMapping("/addBusstop/{trackId}/{busstopId}")
    public String addBusstop(@PathVariable Long trackId, @PathVariable Long busstopId, @RequestParam Double orderNo) {
        Track track = trackRepository.findOne(trackId);
        Busstop busstop = busstopRepository.findOne(busstopId);
        if (orderNo == null) {
            orderNo = (double) routeNodeRepository.countByTrack(track) + 1;
        } else {
            orderNo -= .5;
        }

        RouteNode routeNode = new RouteNode();
        routeNode.setTrack(track);
        routeNode.setBusstop(busstop);
        routeNode.setOrderNo(orderNo);

        routeNodeRepository.save(routeNode);

        reorderBusstops(track);

        return "track/form";
    }

//    @GetMapping("/shiftBusstop/{trackId}/{busstopId}/{direction}")
//    public String addBusstop(@PathVariable Long trackId, @PathVariable Long busstopId, @RequestParam Double orderNo) {
//        Track track = trackRepository.findOne(trackId);
//        Busstop busstop = busstopRepository.findOne(busstopId);
//        if (orderNo == null) {
//            orderNo = (double) routeNodeRepository.countByTrack(track) + 1;
//        } else {
//            orderNo -= .5;
//        }
//
//        RouteNode routeNode = new RouteNode();
//        routeNode.setTrack(track);
//        routeNode.setBusstop(busstop);
//        routeNode.setOrderNo(orderNo);
//
//        routeNodeRepository.save(routeNode);
//
//        reorderBusstops(track);
//
//        return "track/form";
//    }

    private void reorderBusstops(Track track) {
        List<RouteNode> routeNodes = routeNodeRepository.findByTrack(track);
        for (int i = 0; i < routeNodes.size(); i++) {
            routeNodes.get(i).setOrderNo((double) i + 1);
        }
        routeNodeRepository.save(routeNodes);
    }

}
