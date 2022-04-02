package uz.pdp.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationship.entity.Faculty;
import uz.pdp.appjparelationship.entity.University;
import uz.pdp.appjparelationship.payload.FacultyDto;
import uz.pdp.appjparelationship.repository.FacultyRepository;
import uz.pdp.appjparelationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "this university such faculty exists";


        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "university not found";
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);

        return "Faculty saved";

    }

    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            facultyRepository.deleteById(id);
            return "faculty deleted";
        }
        return "faculty not found";
    }

    @GetMapping(value = "/{id}")
    public Faculty getFaculty(@PathVariable Integer id){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);

        if(optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            return faculty;
        }
        return new Faculty();
    }


    @GetMapping(value = "/byUniversity/{id}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;


    }

    @DeleteMapping(value = "/{id}")
    public String DeleteFaculty(@PathVariable Integer id){
        try {
            facultyRepository.deleteById(id);
            return "faculty deleted";
        }catch (Exception e){
            return "error deleting";
        }

    }

    @PutMapping("/{id}")
    public String EditFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()){
                return "university not fouynd";
        }
                faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";

        }
        return "faculty not found";
    }




}
