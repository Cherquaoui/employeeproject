package orm.labo.testhibernate.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import orm.labo.testhibernate.entity.Car;
import orm.labo.testhibernate.entity.Person;
import orm.labo.testhibernate.repo.CarRepository;
import orm.labo.testhibernate.repo.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Car> cars = new ArrayList<>();
        Car c1 = new Car(null,"Renault Clio",4l,null);

        cars.add(c1);
        Person p1 = new Person(null,"habib", "Casa",cars);
        c1.setPerson(p1);

        personRepository.save(p1);
    }
}
