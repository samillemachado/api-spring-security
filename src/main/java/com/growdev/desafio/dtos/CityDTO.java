package com.growdev.desafio.dtos;

import com.growdev.desafio.entities.City;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CityDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    public CityDTO(City city){
        this.id = city.getId();
        this.name = city.getName();
    }
}
