package ilerijavaguzfinal.HR220059.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OyuncakDTO {

    public OyuncakDTO(Long id, String ad, String tur, Integer desi, Double alisFiyati, String notlar, LocalDate alisTarihi) {
        this.id=id;
        this.Ad=ad;
        this.AlisFiyati=alisFiyati;
        this.Tur=tur;
        this.Desi=desi;
        this.Notlar=notlar;
        this.alisTarihi=alisTarihi;
    }

    private Long id;
    private Double AlisFiyati;
    private String Ad;
    private String Tur;
    private Integer Desi;
    private String Notlar;
    private LocalDate alisTarihi;

}
