package uz.pdp.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.appjparelationship.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
