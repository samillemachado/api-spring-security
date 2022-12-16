package com.growdev.desafio.services;

import com.growdev.desafio.dtos.RoleDTO;
import com.growdev.desafio.dtos.UserDTO;
import com.growdev.desafio.entities.Role;
import com.growdev.desafio.entities.User;
import com.growdev.desafio.exceptions.DatabaseException;
import com.growdev.desafio.repositories.RoleRepository;
import com.growdev.desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> listaUser = repository.findAll(pageable);
        return listaUser.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        User user = repository.findById(id).get();
        return new UserDTO(user);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch ( Exception e ) {
            throw new DatabaseException( e.getMessage() );
        }
    }

    public UserDTO save (UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPass(passwordEncoder.encode(userDTO.getPass()));

        for (RoleDTO roleDTO : userDTO.getRoles()) {
            Role role = roleRepository.findById(roleDTO.getId()).get();
            user.getRoles().add(role);
        }

        user = repository.save(user);
        return new UserDTO(user);
    }

    public UserDTO update (UserDTO userDTO, Long id){
        User user = repository.findById(id).get();
        user.setEmail(userDTO.getEmail());
        user.setPass(passwordEncoder.encode(userDTO.getPass()));

        for (RoleDTO roleDTO : userDTO.getRoles()) {
            Role role = roleRepository.findById(roleDTO.getId()).get();
            user.getRoles().add(role);
        }

        user = repository.save(user);
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = repository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Email não existe");
        }
        return user;
    }
}
