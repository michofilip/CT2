package com.example.ct2.schedule;

import com.example.ct2.ride.Ride;
import com.example.ct2.routenode.RouteNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
@ToString
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    private RouteNode routeNode;

    @ManyToOne
    private Ride ride;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

}
