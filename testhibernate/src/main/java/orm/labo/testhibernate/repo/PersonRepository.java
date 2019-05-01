package orm.labo.testhibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orm.labo.testhibernate.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
