package com.growdev.desafio.services;

import com.growdev.desafio.dtos.EventDTO;
import com.growdev.desafio.entities.City;
import com.growdev.desafio.entities.Event;
import com.growdev.desafio.exceptions.DatabaseException;
import com.growdev.desafio.exceptions.ResourceNotFoundException;
import com.growdev.desafio.repositories.CityRepository;
import com.growdev.desafio.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EventService {
    @Autowired
    private CityRepository repositoryCity;

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(PageRequest pageable){
        Page<Event> listaEvent = repository.findAll(pageable);
        return listaEvent.map(EventDTO::new);
    }

    @Transactional(readOnly = true)
    public EventDTO findById(Long id){
        Event event = repository.findById(id).get();
        return new EventDTO(event);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch ( Exception e ) {
            throw new DatabaseException( e.getMessage() );
        }
    }

    public EventDTO save (EventDTO eventDTO){
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());

        //verifico se existe a City a ser cadastrada, no cityRepository:
        City city = repositoryCity.findById(eventDTO.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Not found " + eventDTO.getCityId()));

        event.setCity(city);
        event = repository.save(event);
        return new EventDTO(event);
    }

    public EventDTO update (EventDTO eventDTO, Long id){
        Event event = repository.findById(eventDTO.getCityId()).get();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());

        City city = repositoryCity.findById(eventDTO.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Not found " + eventDTO.getCityId()));

        event.setCity(city);
        event = repository.save(event);
        return new EventDTO(event);
    }
}
