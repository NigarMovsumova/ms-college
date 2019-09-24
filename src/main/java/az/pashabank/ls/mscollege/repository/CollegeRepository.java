package az.pashabank.ls.mscollege.repository;

import az.pashabank.ls.mscollege.repository.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {

    CollegeEntity getCollegeEntityByNameAndCity(String name, String city);

    List<CollegeEntity> getCollegeEntitiesByCity(String name);
}
