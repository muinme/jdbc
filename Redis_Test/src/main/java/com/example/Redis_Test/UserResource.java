package com.example.Redis_Test;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("")
public class UserResource {
    private UserRepository userRepository;
    public UserResource(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userRepository.save(user);
    }
    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id, @PathVariable("name") final String name){

        userRepository.save(new User(id,name, 1000L));
        return userRepository.findById(id);
    }
    @GetMapping("/all")
        public Map<String, User> getAll(){
        return userRepository.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteFruit(@PathVariable String id){
        userRepository.delete(id);
        return "fruit delete with id :" + id;
    }
}
