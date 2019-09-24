package az.pashabank.ls.mscollege.service;

import az.pashabank.ls.mscollege.exception.NotFoundException;
import az.pashabank.ls.mscollege.mappers.CollegeMapper;
import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.repository.entity.CollegeEntity;
import az.pashabank.ls.mscollege.repository.CollegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    private static final Logger logger = LoggerFactory.getLogger(CollegeService.class);
    private final CollegeRepository collegeRepository;
    private final CollegeMapper collegeMapper;


    public CollegeService(CollegeRepository collegeRepository,
                          CollegeMapper collegeMapper) {
        this.collegeRepository = collegeRepository;
        this.collegeMapper = collegeMapper;
    }

    public CollegeDto getCollegeById(Long id) {

        CollegeEntity collegeEntity = collegeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("College with id: " + id + " is not found"));

        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    public CollegeDto createCollege(CollegeRequest collegeRequest) {
        CollegeEntity collegeEntity = CollegeEntity
                .builder()
                .name(collegeRequest.getName())
                .city(collegeRequest.getCity())
                .build();

        if (collegeRepository.getCollegeEntityByNameAndCity(collegeRequest.getName(), collegeRequest.getCity()) == null) {
            collegeRepository.save(collegeEntity);
        } else {
            logger.debug("No colleges with name {} are found in {}", collegeRequest.getName(), collegeRequest.getCity());
        }
        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    public CollegeDto deleteCollege(Long id) {
        CollegeEntity collegeEntity = collegeRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException("College with id {}"+id+ " not found"));

        collegeRepository.delete(collegeEntity);
        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    public CollegeDto updateCollege(Long id, CollegeRequest collegeRequest) {
        CollegeEntity collegeEntity = collegeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("College with id: " + id + " is not found"));

        if (collegeRequest.getName() != null || !collegeRequest.getName().equals("")) {
            collegeEntity.setName(collegeRequest.getName());
        }
        if (collegeRequest.getCity() != null || !collegeRequest.getCity().equals("")) {
            collegeEntity.setCity(collegeRequest.getCity());
        }

        collegeRepository.save(collegeEntity);
        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    public List<CollegeDto> getCollegesByLocation(String city) {
        List<CollegeEntity> collegeEntities = collegeRepository.getCollegeEntitiesByCity(city);

        return collegeMapper.mapEntityListToDtoList(collegeEntities);
    }
}
