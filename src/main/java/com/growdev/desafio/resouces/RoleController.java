package com.growdev.desafio.resouces;

import com.growdev.desafio.dtos.RoleDTO;
import com.growdev.desafio.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable){
        Page<RoleDTO> listaRole = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(listaRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id){
        RoleDTO roleDTO = service.findById(id);
        return ResponseEntity.ok().body(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO roleDTO){
        RoleDTO roleDTO1 = service.save(roleDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleDTO1.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO roleDTO, @PathVariable Long id){
        RoleDTO roleDTO1 = service.update(roleDTO, id);
        return ResponseEntity.ok().body(roleDTO1);
    }
}
