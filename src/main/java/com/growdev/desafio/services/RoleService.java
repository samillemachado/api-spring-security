package com.growdev.desafio.services;

import com.growdev.desafio.dtos.RoleDTO;
import com.growdev.desafio.entities.Role;
import com.growdev.desafio.exceptions.DatabaseException;
import com.growdev.desafio.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable){
        Page<Role> listaRole = repository.findAll(pageable);
        return listaRole.map(RoleDTO::new);
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id){
        Role role = repository.findById(id).get();
        return new RoleDTO(role);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch ( Exception e ) {
            throw new DatabaseException( e.getMessage() );
        }
    }

    public RoleDTO save (RoleDTO roleDTO){
        Role role = new Role();
        role.setAuthority(roleDTO.getAuthority());
        role.setUsers(roleDTO.getUsers());
        role = repository.save(role);
        return new RoleDTO(role);
    }

    public RoleDTO update (RoleDTO roleDTO, Long id){
        Role role = repository.findById(id).get();
        role.setAuthority(roleDTO.getAuthority());
        role.setUsers(roleDTO.getUsers());
        role = repository.save(role);
        return new RoleDTO(role);
    }
}
