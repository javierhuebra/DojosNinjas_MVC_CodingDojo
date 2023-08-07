package com.codingdojo.dojosyninjas.controllers;

import com.codingdojo.dojosyninjas.models.Dojo;
import com.codingdojo.dojosyninjas.models.Ninja;
import com.codingdojo.dojosyninjas.services.MainService;
import com.sun.tools.javac.Main;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    //Mostrar todos los ninjas
    @GetMapping("/")
    public String index(Model viewModel){
        List<Ninja> todosLosNinjas = mainService.allNinjas();
        viewModel.addAttribute("ninjas",todosLosNinjas);
        return "index";
    }

    //Mostrar el detalle de los ninjas
    @GetMapping("/ninjas/{id}")
    public String buscarPorId(@PathVariable("id") Long id,Model viewModel){
        Ninja ninja = mainService.buscarPorId(id);
        viewModel.addAttribute("ninjaPorId", ninja);

        return "detail";
    }

    //Controladores de DOJO --------------------------------------------------------------------
    @GetMapping("/dojos/new")
    public String newDojo(@ModelAttribute("dojo") Dojo dojo){
        return "dojonew";
    }

    @PostMapping("/dojos/new")
    public String newDojoPost(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/dojos/new";
        }else{
            String dojoName = dojo.getName();
            Dojo dojoNew = new Dojo();
            dojoNew.setName(dojoName);
            System.out.println(dojoName);
            mainService.createDojo(dojoNew);
            return "redirect:/";
        }
    }

    //Controladores de Ninja--------------------------------------------------------------------------
    @GetMapping("/ninjas/new")
    public String newNinja(Model model){
        List<Dojo> dojos = mainService.allDojos();
        //Traigo la lista de dojos que ya existen y los meto en el model con el nombre "dojos" y abajo instancio un nuevo ninja vacio para capturar los datos con el form en el post
        model.addAttribute("dojos", dojos);
        model.addAttribute("newNinja", new Ninja());
        return "ninjanew";
    }

    @PostMapping("/ninjas/new")
    public String newNinjaPost(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/ninjas/new";
        }else{
            mainService.createNinja(ninja);
            return "redirect:/";
        }
    }

}
