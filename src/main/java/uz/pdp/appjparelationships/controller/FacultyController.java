package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.FacultyDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;
//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId()); // JPA query
        if(exists)
            return "This faculty already exits in this university";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
//        University university = universityRepository.findById(facultyDto.getUniversityId()).orElseThrow(() -> new IllegalStateException("University not found"));
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if(!optionalUniversity.isPresent())
            return "University not found";
        faculty.setUniversity(optionalUniversity.get());



        facultyRepository.save(faculty);
        return "Faculty saved!";
    }

    //Hamma univerlani ko'rish
    //Vzirlik uchun
    @GetMapping
    public List<Faculty> getFAculties() {
        return facultyRepository.findAll();
    }

    //Biror univerni faqat o'ziga tegishli facultetlarni berish
    // Biror univer xodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversity(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted!";
        } catch (Exception e) {
            return "Error in deleting!";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if(!optionalUniversity.isPresent())
                return "University not found!";
            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "Faculty edited!";
        }
        return "Faculty not found!";
    }

}
