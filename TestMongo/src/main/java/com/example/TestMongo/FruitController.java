package com.example.TestMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class FruitController {

    @Autowired
    public FruitRepository fruitRepository;

    @GetMapping(value = "/all")
    public List<Fruit> getAllFruits(){
        return fruitRepository.findAll();
    }

    @GetMapping("/findFruit/{id}")
    public Optional<Fruit> getFruit(@PathVariable String id){
        return  fruitRepository.findById(id);
    }

    @PostMapping(value = "/create")
    public String createFruit(@RequestBody Fruit fruit){
        Fruit insertFruit = fruitRepository.insert(fruit);
        return "Fruit created: "+ insertFruit.getId();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Fruit fruit, @PathVariable String id)
{
    try {
        Fruit existFruit = fruitRepository.findById(id).get();
        fruitRepository.save(fruit);
        return new ResponseEntity<>(HttpStatus.OK);
    }catch (NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("/delete/{id}")
    public String deleteFruit(@PathVariable String id){
        fruitRepository.deleteById(id);
        return "fruit delete with id :" + id;
    }
}

