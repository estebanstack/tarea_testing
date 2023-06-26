package com.example.LaptopExercise.Controllers;

import com.example.LaptopExercise.Entities.Laptop;
import com.example.LaptopExercise.Repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private final LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("api/laptops/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id){
       Optional<Laptop> laptopOptional = laptopRepository.findById(id);

       return laptopOptional.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        for(Laptop laptopActual : laptopRepository.findAll()){
            if(laptop.getModel().equalsIgnoreCase(laptopActual.getModel()))
                return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            log.warn("trying to update a non existed laptop");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("trying to update a non existed laptop");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteOne(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            log.warn("trying to delete a non existed laptop");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Laptop> deleteAll(){
        if(laptopRepository.count() == 0 ){
            log.warn("DataBase is already void");
            return ResponseEntity.noContent().build();
        }
        laptopRepository.deleteAll();

        return ResponseEntity.noContent().build();

    }


}
