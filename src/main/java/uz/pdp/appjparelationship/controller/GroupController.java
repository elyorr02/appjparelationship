package uz.pdp.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationship.entity.Faculty;
import uz.pdp.appjparelationship.entity.Group;
import uz.pdp.appjparelationship.payload.GroupDto;
import uz.pdp.appjparelationship.repository.FacultyRepository;
import uz.pdp.appjparelationship.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")
public class GroupController {


    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping
    public List<Group> getGroups(){
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    @GetMapping(value = "/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
//        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_universityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){


        Group group=new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "such faculty not found";
        }

        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return "group added";
    }

}
