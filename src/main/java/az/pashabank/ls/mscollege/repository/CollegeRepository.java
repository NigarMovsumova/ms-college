package az.pashabank.ls.mscollege.repository;

import az.pashabank.ls.mscollege.model.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {

    CollegeEntity getCollegeEntityByNameAndCity(String name, String city);
}
