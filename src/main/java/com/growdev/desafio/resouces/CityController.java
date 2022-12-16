package com.growdev.desafio.resouces;

import com.growdev.desafio.dtos.CityDTO;
import com.growdev.desafio.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService service;

    // Pageable já me retorna um padrão, sem a url
    //caso queira retornar a página manipulada, passa a url
    //no sort da url, podemos passar qualquer atributo
    @GetMapping //url no postman = ?page=0&size=12&sort=name,asc
    public ResponseEntity<Page<CityDTO>> findAll(Pageable pageable){
        Page<CityDTO> listaCity = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(listaCity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id){
        CityDTO cityDTO = service.findById(id);
        return ResponseEntity.ok().body(cityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CityDTO> save(@Valid @RequestBody CityDTO cityDTO){
        CityDTO cityDTO1 = service.save(cityDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cityDTO1.getId()).toUri();
        return ResponseEntity.created(uri).body(cityDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@Valid @RequestBody CityDTO cityDTO, @PathVariable Long id){
        CityDTO cityDTO1 = service.update(cityDTO, id);
        return ResponseEntity.ok().body(cityDTO1);
    }
}
