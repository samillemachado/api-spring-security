package com.growdev.desafio.dtos;

import com.growdev.desafio.entities.City;
import com.growdev.desafio.entities.Event;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EventDTO implements Serializable {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Future
    private LocalDate date;

    private String url;

    @NotNull
    private Long cityId;

    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.date = event.getDate();
        this.cityId = event.getCity().getId();
    }

}
