package uz.pdp.appjparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne //MANY group TO ONE faculty. Ko'plab guruhlar bitta faktultetga tegishli
    private Faculty faculty;
//
//    @OneToMany//ONE group TO MANY students
//    private List<Student> students;   //Student students; list qilmay bunday ochsa xato beradi
}
