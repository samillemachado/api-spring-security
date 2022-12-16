package com.growdev.desafio.services;

import com.growdev.desafio.dtos.CityDTO;
import com.growdev.desafio.entities.City;
import com.growdev.desafio.exceptions.DatabaseException;
import com.growdev.desafio.exceptions.ResourceNotFoundException;
import com.growdev.desafio.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityService {
    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public Page<CityDTO> findAllPaged(Pageable pageable){
        Page<City> listaCity = repository.findAll(pageable);
        return listaCity.map(CityDTO::new);
    }

    @Transactional(readOnly = true)
    public CityDTO findById(Long id){
        City city = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("NOT FOUND: " + id));;
        return new CityDTO(city);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch ( Exception e ) {
            throw new DatabaseException( e.getMessage() );
        }
    }

    public CityDTO save (CityDTO cityDTO){
        City city = new City();
        city.setName(cityDTO.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }

    public CityDTO update (CityDTO cityDTO, Long id){
        City city = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("NOT FOUND: " + id));
        city.setName(cityDTO.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }
}
