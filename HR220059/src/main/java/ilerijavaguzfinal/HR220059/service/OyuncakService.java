package ilerijavaguzfinal.HR220059.service;

import ilerijavaguzfinal.HR220059.entity.Oyuncak;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OyuncakService {
    Oyuncak ekle(Oyuncak oyuncak);
    Oyuncak getir(Long id);
    void sil(Long id);
    Oyuncak guncelle(Oyuncak oyuncak);
    List<Oyuncak> listele();

}
