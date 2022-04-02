package uz.pdp.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationship.entity.Subject;
import uz.pdp.appjparelationship.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "this is subject already exists";

        subjectRepository.save(subject);

        return "subject added";

    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Subject getStudent(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if (optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            return subject;

        }else {
            return new Subject();
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSubject(@PathVariable Integer id){
        boolean existsByName = subjectRepository.existsById(id);
         if (existsByName) {
             subjectRepository.deleteById(id);
             return "student deleted";
         }

         return "this not found";


    }

    @PutMapping(value = "/{id}")
    public String EditingSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject editingSubject = optionalSubject.get();
            editingSubject.setName(subject.getName());

            subjectRepository.save(editingSubject);
            return "subject edited";

        }
        return "this not found";
    }


}
