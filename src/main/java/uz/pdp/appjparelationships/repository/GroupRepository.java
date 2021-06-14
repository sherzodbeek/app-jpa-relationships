package uz.pdp.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appjparelationships.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {


    // Universitetning guruhlarini olish uchun 3 xil query
    List<Group> findAllByFaculty_UniversityId(Integer faculty_university_id); //JPA orqali query

    @Query("select gr from groups gr where gr.faculty.university.id=:universityId")
    List<Group> getGroupsByUniversityId(Integer universityId);

    //NATIVE QUERY ORQALI
    @Query(value = "select *\n" +
            "from groups g\n" +
            "         join faculty f on f.id = g.faculty_id\n" +
            "         join university u on u.id = f.university_id\n" +
            "where u.id=:universityId", nativeQuery = true)
    List<Group> getGroupsByUniversityIdNative(Integer universityId);
}
