package ilerijavaguzfinal.HR220059.Controller;

import ilerijavaguzfinal.HR220059.dto.OyuncakDTO;
import ilerijavaguzfinal.HR220059.entity.Oyuncak;
import ilerijavaguzfinal.HR220059.service.OyuncakServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class Veri {

    @Autowired
    OyuncakServiceImpl oyuncakService;

    @PostMapping("/ekle")
    public String ekle(@RequestBody OyuncakDTO oyuncakDTO) {
        log.info("ekle servisi çağrıldı." + LocalDate.now());
        try{
            Oyuncak oyuncak = new Oyuncak();
            oyuncak.setAd(oyuncakDTO.getAd());
            oyuncak.setTur(oyuncakDTO.getTur());
            oyuncak.setDesi(oyuncakDTO.getDesi());
            oyuncak.setAlisFiyati(oyuncakDTO.getAlisFiyati());
            oyuncak.setNotlar(oyuncakDTO.getNotlar());
            oyuncak.setAlisTarihi(LocalDate.now());
            Oyuncak kaydedilmisOyuncak = oyuncakService.ekle(oyuncak);
            return kaydedilmisOyuncak.getAd() + " adlı yeni bir oyuncak oluşturuldu.";
        } catch (Exception e) {
            log.error("ekle servisi çalışırken hata aldı. "
                        + "Parametreler:" + oyuncakDTO
                        + "Hata:" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/getir")
    public OyuncakDTO getir(Long id) {
        log.info("getir servisi çağrıldı." + LocalDate.now());
        Oyuncak oyuncak = oyuncakService.getir(id);
        OyuncakDTO oyuncakDTO = new OyuncakDTO(oyuncak. getID(),oyuncak.getAd(), oyuncak.getTur(), oyuncak.getDesi(), oyuncak.getAlisFiyati(), oyuncak.getNotlar(), oyuncak.getAlisTarihi());
        return oyuncakDTO;
    }

    @DeleteMapping ("/sil")
    public String sil(Long id) {
        log.info("sil servisi çağrıldı." + LocalDate.now());
        if (id < 1) {
            return "ID bilgisi boş olamaz!";
        } else {
            oyuncakService.sil(id);
        }
        return id + " id nolu oyuncak silindi.";
    }

    @PutMapping("/guncelle/{id}")
    public void guncelle(@PathVariable Long id, @RequestBody OyuncakDTO OyuncakDTO) {
        log.info("güncelle servisi çağrıldı." + LocalDate.now());
        Oyuncak oyuncak = oyuncakService.getir(id);
        oyuncak.setAd(OyuncakDTO.getAd());
        oyuncak.setTur(OyuncakDTO.getTur());
        oyuncak.setDesi(OyuncakDTO.getDesi());
        oyuncak.setAlisFiyati(OyuncakDTO.getAlisFiyati());
        oyuncak.setNotlar(OyuncakDTO.getNotlar());
        oyuncak.setAlisTarihi(LocalDate.now());
        oyuncakService.guncelle(oyuncak);
    }

    @GetMapping("/listele")
    public List<OyuncakDTO> listele() {
        log.info("listele servisi çağrıldı." + LocalDate.now());
        List<Oyuncak> oyuncakList = oyuncakService.listele();
        List<OyuncakDTO> tumOyuncaklar = new ArrayList<>();
        for (Oyuncak o : oyuncakList) {
            OyuncakDTO oDTO = new OyuncakDTO(o.getID(), o.getAd(), o.getTur(), o.getDesi(), o.getAlisFiyati(), o.getNotlar(), o.getAlisTarihi());
            tumOyuncaklar.add(oDTO);
        }
        return tumOyuncaklar;
    }
}
