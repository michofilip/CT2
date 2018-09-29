package com.example.ct2.busstop;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "busstops")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Busstop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    String name;

}
