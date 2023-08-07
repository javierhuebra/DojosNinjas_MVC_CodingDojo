package com.codingdojo.dojosyninjas.services;

import com.codingdojo.dojosyninjas.models.Dojo;
import com.codingdojo.dojosyninjas.models.Ninja;
import com.codingdojo.dojosyninjas.repositories.DojoRepository;
import com.codingdojo.dojosyninjas.repositories.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    private final DojoRepository dojoRepository;
    private final NinjaRepository ninjaRepository;
    public MainService(DojoRepository dojoRepository,NinjaRepository ninjaRepository){
        this.dojoRepository = dojoRepository;
        this.ninjaRepository = ninjaRepository;
    }

    //SERVICIOS DE DOJO--------------------------------

    //Obtener todos los dojos
    public List<Dojo> allDojos(){
        return dojoRepository.findAll();
    }

    //Crear Dojo
    public Dojo createDojo(Dojo d){
        return dojoRepository.save(d);
    }

    //Buscar dojo por id
    public Dojo buscarDojo(Long id){
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        return optionalDojo.orElse(null);
    }

    //SERVICIOS DE NINJA--------------------------------

    //Obtener todos los ninjas
    public List<Ninja> allNinjas(){
        return ninjaRepository.findAll();
    }

    //Crear Ninja
    public Ninja createNinja(Ninja n){
        return ninjaRepository.save(n);
    }

    //Buscar Ninja por Id
    public Ninja buscarPorId(Long id){
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        return optionalNinja.orElse(null);
    }
}
