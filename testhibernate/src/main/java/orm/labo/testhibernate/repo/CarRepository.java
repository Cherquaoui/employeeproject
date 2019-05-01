package orm.labo.testhibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orm.labo.testhibernate.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
