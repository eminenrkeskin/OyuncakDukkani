package ilerijavaguzfinal.HR220059.repo;

import ilerijavaguzfinal.HR220059.entity.Oyuncak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OyuncakRepository extends JpaRepository<Oyuncak, Long> {

}
