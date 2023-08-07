package com.codingdojo.dojosyninjas.repositories;

import com.codingdojo.dojosyninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NinjaRepository extends CrudRepository<Ninja,Long> {
    List<Ninja> findAll();
}
