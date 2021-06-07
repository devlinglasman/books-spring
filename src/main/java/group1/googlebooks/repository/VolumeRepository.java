package group1.googlebooks.repository;

import group1.googlebooks.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumeRepository extends JpaRepository<Volume, Long> {

}
