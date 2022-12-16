package com.growdev.desafio.resouces;

import com.growdev.desafio.dtos.CityDTO;
import com.growdev.desafio.dtos.EventDTO;
import com.growdev.desafio.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService service;

    //Com o @RequestParam, se não passar a url, ela vai o defaultValue
    //caso queira retornar a página manipulada, passa a url com values dos params personalizados
    @GetMapping //url no postman = ?pagina=0&linhasPorPagina=10&ordenado=name&direcao=asc
    public ResponseEntity<Page<EventDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "10") Integer linhasPorPagina,
            @RequestParam(value = "direcao", defaultValue = "asc") String direcao,
            @RequestParam(value = "ordenado", defaultValue = "name") String name
    ){
        PageRequest pageable = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), name);
        Page<EventDTO> listaEvento = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(listaEvento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id){
        EventDTO eventDTO = service.findById(id);
        return ResponseEntity.ok().body(eventDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EventDTO> save(@Valid @RequestBody EventDTO eventDTO){
        EventDTO eventDTO1 = service.save(eventDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDTO1.getId()).toUri();
        return ResponseEntity.created(uri).body(eventDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@Valid @RequestBody EventDTO eventDTO, @PathVariable Long id){
        EventDTO eventDTO1 = service.update(eventDTO, id);
        return ResponseEntity.ok().body(eventDTO1);
    }
}
