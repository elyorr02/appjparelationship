package uz.pdp.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjparelationship.entity.Group;
import uz.pdp.appjparelationship.entity.University;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculty_UniversityId(Integer faculty_university_id);

//    @Query("select gr from groups gr where gr.faculty.university.id=:unversityId")
//    List<Group> getGroupsByUniversityId(Integer universityId);


//    @Query(value = "select *\n " +
//            "from groups g\n" +
//            "             join faculty f on  f.id = g.faculty_id\n" +
//            "              join university u on  u.id = f.university_id\n" +
//            " where u.id=:universityId", nativeQuery = true)
//    List<Group> getGroupsByUniversityIdNative(Integer universityId);
}
