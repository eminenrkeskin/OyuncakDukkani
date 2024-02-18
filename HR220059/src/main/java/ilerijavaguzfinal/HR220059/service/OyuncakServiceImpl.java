package ilerijavaguzfinal.HR220059.service;

import ilerijavaguzfinal.HR220059.entity.Oyuncak;
import ilerijavaguzfinal.HR220059.repo.OyuncakRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OyuncakServiceImpl implements OyuncakService {

    @Autowired
    OyuncakRepository oyuncakRepo;

    @Override
    public Oyuncak ekle(Oyuncak oyuncak) {
        return oyuncakRepo.save(oyuncak);
    }

    @Override
    public Oyuncak getir(Long id) {
        return oyuncakRepo.getReferenceById(id);
    }

    @Override
    public void sil(Long id) {
        oyuncakRepo.deleteById(id);
    }

    @Override
    public Oyuncak guncelle(Oyuncak oyuncak) {
        return oyuncakRepo.save(oyuncak);
    }

    @Override
    public List<Oyuncak> listele() {
        return oyuncakRepo.findAll();
    }
}
