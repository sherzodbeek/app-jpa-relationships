package uz.pdp.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UniversityDto { // MA'LUMOTLARNI TASHIHS UCHUN XIZMAT QILADI
    private String name;
    private String city;
    private String district;
    private String street;
}
