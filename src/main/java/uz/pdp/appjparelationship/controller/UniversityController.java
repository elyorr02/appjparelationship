package uz.pdp.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationship.entity.Address;
import uz.pdp.appjparelationship.entity.University;
import uz.pdp.appjparelationship.payload.UniversityDto;
import uz.pdp.appjparelationship.repository.AddressRepository;
import uz.pdp.appjparelationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());

        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University saved";

    }


    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "University deleted";

    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {

            University editingUniversity = optionalUniversity.get();
            editingUniversity.setName(universityDto.getName());

            Address editingAddress= editingUniversity.getAddress();
            editingAddress.setCity(universityDto.getCity());
            editingAddress.setDistrict(universityDto.getDistrict());
            editingAddress.setStreet(universityDto.getStreet());

            addressRepository.save(editingAddress);

            universityRepository.save(editingUniversity);
            return "saved university";

        }
        return "university not found";
    }

}
