package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    // STUDENTLARNI KO'RISH
    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0
        // select * from student limit 10 offset 0  // page 0
        //2-1=1
        // select * from student limit 10 offset 1*10  // page 1
        // 3-1=2
        // select * from student limit 10 offset 2*10  // page 2
        // 4-1=3
        // select * from student limit 10 offset 3*10  // page 3

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getGetStudentListForUniversity(@PathVariable Integer universityId,
                                                   @RequestParam int page) {
        //1-1=0
        // select * from student limit 10 offset 0  // page 0
        //2-1=1
        // select * from student limit 10 offset 1*10  // page 1
        // 3-1=2
        // select * from student limit 10 offset 2*10  // page 2
        // 4-1=3
        // select * from student limit 10 offset 3*10  // page 3

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    //3. FACULTY DEKANAT
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentListForFaculty(@PathVariable Integer facultyId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
    }

    //4.GROUP OWNER
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentListForGroup(@PathVariable Integer groupId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroupId(groupId, pageable);
    }

    @GetMapping("/oneStudent/{id}")
    public Student getOneStudentById(@PathVariable Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student not found!"));
    }





}
