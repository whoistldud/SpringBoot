package com.example.firstproject.api;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @GetMapping("api/coffees")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }
}
