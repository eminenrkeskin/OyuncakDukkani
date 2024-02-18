package ilerijavaguzfinal.HR220059.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Oyuncak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDate AlisTarihi;
    private Double AlisFiyati;
    private String Ad;
    private String Tur;
    private Integer Desi;
    private String Notlar;

}

