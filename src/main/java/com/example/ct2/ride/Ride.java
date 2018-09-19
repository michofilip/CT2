package com.example.ct2.ride;

import com.example.ct2.bus.Bus;
import com.example.ct2.track.Track;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rides")
@Data
@ToString
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    private Track track;

    @ManyToOne
    private Bus bus;

    @NotNull
    private Double rideNo;

}
