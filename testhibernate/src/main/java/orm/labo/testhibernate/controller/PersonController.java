package orm.labo.testhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import orm.labo.testhibernate.entity.Person;
import orm.labo.testhibernate.repo.PersonRepository;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
}
