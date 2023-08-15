package com.codingdojo.dojosyninjas.repositories;

import com.codingdojo.dojosyninjas.models.Dojo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DojoRepository extends CrudRepository<Dojo,Long> {
    List<Dojo> findAll();

    //CONSULTAS PERSONALIZADAS --------------------------
    //Obtener todos los dojos
    @Query("SELECT d FROM Dojo d")
    List<Dojo> queryAllDojos();

    //Obtener todos los nombres de los dojos
    @Query("SELECT d.name FROM Dojo d")
    List<Dojo> queryAllNamesDojos();

    // Pasar parametros y filtrar, sigue siendo una lista todavia
    @Query("SELECT d FROM Dojo d WHERE d.id = :idDojo")
    List<Dojo> queryGetDojoWhereId(Long idDojo);

    // Pasar parametros y filtrar pero devuelve uno, no una lista
    @Query("SELECT d FROM Dojo d WHERE d.id = :idDojo")
    Dojo queryGetDojoIndividual(Long idDojo);

    //QUERY PARA MODIFICACIONES--------------------------
    @Modifying
    @Query("UPDATE Dojo d SET d.name = ?1 WHERE d.id = ?2") //Parece que ?1 y ?2 toman los lugares de la funcion
    int setNameForOne(String name, Long id);

    @Modifying
    @Query("UPDATE Dojo d set d.name = ?1")
    int setNameForAll(String name);

    @Modifying
    @Query("DELETE Dojo d WHERE d.id = ?1")
    int deleteOneDojo(Long id);

    //CONSULTAS NATIVAS-----------------------------------

    //Traer la lista de dojos con consulta nativa (Probar a ver si anda)
    @Query(value="SELECT * FROM dojos", nativeQuery = true)
    List<Dojo> findAllDojosWhitNativeQuery();

    // Obtener todos los nombres y el id de los dojos. Si queremos seleccionar una columna específica, obtenemos     una arreglo de objetos porque ya no son objetos Dojos. Cada índice del arreglo será la columna seleccionada      respectivamente, por lo tanto, 0 = columna id, 1 = columna nombre.
    @Query(value="SELECT id, name from dojos", nativeQuery=true)
    List<Object[]> findAllDojosNamesWithIdNativo();

    // Obtener un dojo.
    @Query(value="SELECT * FROM dojos WHERE id = ?1", nativeQuery=true)
    Dojo getDojoWhereId(Long id);

    /*
    List<Object[]> dojos = dojoRepo.findAllDojosNamesWithId2();
    Object[] dojo = dojos.get(0);
    Object dojoId = dojo[0];
    Object dojoName = dojo[1];
    */


    //JOINS-----------------------------------------------
    // Inner Join para obtener solo los dojos
    @Query("SELECT d FROM Dojo d JOIN d.ninja n")//Le pongo la tabla que sale de Ninja que es una lista
    List<Dojo> joinDojosAndNinjas();

    // Inner join para obtener los dojos y los ninjas
    @Query("SELECT d, n FROM Dojo d JOIN d.ninja n")
    List<Object[]> joinDojosAndNinjas2();

    /*
    List<Object[]> table = dojoRepo.joinDojosAndNinjas2();
    for(Object[] row : table) {
        Dojo dojo = (Dojo) row[0];
        Ninja ninja = (Ninja) row[1];
        System.out.println(dojo.getName());
        System.out.println(ninja.getFirstName());
    }
    */

}
