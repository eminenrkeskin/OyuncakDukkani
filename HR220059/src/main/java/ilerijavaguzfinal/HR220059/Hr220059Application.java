package ilerijavaguzfinal.HR220059;

import ilerijavaguzfinal.HR220059.entity.Oyuncak;
import ilerijavaguzfinal.HR220059.repo.OyuncakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Hr220059Application implements CommandLineRunner {

	@Autowired
	OyuncakRepository oyuncakRepository;

	public static void main(String[] args) {
		SpringApplication.run(Hr220059Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Oyuncak oyuncak1 = new Oyuncak();
		oyuncak1.setAd("Süper Kahraman Action Figur");
		oyuncak1.setTur("Action Figur");
		oyuncak1.setDesi(10);
		oyuncak1.setAlisFiyati(400.0);
		oyuncak1.setNotlar("Aksiyon Figürüdür.");
		oyuncak1.setAlisTarihi(LocalDate.now());

		Oyuncak oyuncak2 = new Oyuncak();
		oyuncak2.setAd("1000 Parça Dünya Haritası Puzzle");
		oyuncak2.setTur("Puzzle");
		oyuncak2.setDesi(3);
		oyuncak2.setAlisFiyati(600.0);
		oyuncak2.setNotlar("Zorlu ama eğlenceli bir 1000 parça puzzle.");
		oyuncak2.setAlisTarihi(LocalDate.now());

		oyuncakRepository.save(oyuncak1);
		oyuncakRepository.save(oyuncak2);

	}



}
