package az.pashabank.ls.mscollege.service.impl;


import az.pashabank.ls.mscollege.exception.NotFoundException;
import az.pashabank.ls.mscollege.mappers.CollegeMapper;
import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.repository.CollegeRepository;
import az.pashabank.ls.mscollege.repository.entity.CollegeEntity;
import az.pashabank.ls.mscollege.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    private static final Logger logger = LoggerFactory.getLogger(az.pashabank.ls.mscollege.service.CollegeService.class);
    private final CollegeRepository collegeRepository;
    private final CollegeMapper collegeMapper;


    public CollegeServiceImpl(CollegeRepository collegeRepository,
                              CollegeMapper collegeMapper) {
        this.collegeRepository = collegeRepository;
        this.collegeMapper = collegeMapper;
    }

    @Override
    public CollegeDto getCollegeById(Long id) {
        logger.info("ActionLog.getCollegeById.start : id {}", id);
        CollegeEntity collegeEntity = collegeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("College with id: " + id + " is not found"));
        logger.info("ActionLog.getCollegeById.success : id {}", id);

        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    @Override
    public void createCollege(CollegeRequest collegeRequest) {
        logger.info("ActionLog.createCollege.start");

        CollegeEntity collegeEntity = CollegeEntity
                .builder()
                .name(collegeRequest.getName())
                .city(collegeRequest.getCity())
                .build();

        if (collegeRepository.getCollegeEntityByNameAndCity(collegeRequest.getName(), collegeRequest.getCity()) == null) {
            collegeRepository.save(collegeEntity);
            logger.info("ActionLog.createCollege.success");
        } else {
            logger.debug("No colleges with name {} are found in city {}", collegeRequest.getName(), collegeRequest.getCity());
        }
    }

    @Override
    public void deleteCollege(Long id) {
        logger.info("ActionLog.deleteCollege.start: id {}", id);

        CollegeEntity collegeEntity = collegeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("College with id {}" + id + " not found"));

        collegeRepository.delete(collegeEntity);
        logger.info("ActionLog.createCollege.success: id {}", id);
    }

    @Override
    public void updateCollege(Long id, CollegeRequest collegeRequest) {
        logger.info("ActionLog.updateCollege.start: id{}", id);

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
        logger.info("ActionLog.updateCollege.success: id {}", id);

    }

    @Override
    public List<CollegeDto> getCollegesByLocation(String city) {
        logger.info("ActionLog.getCollegesByLocation.start");
        List<CollegeEntity> collegeEntities = collegeRepository.getCollegeEntitiesByCity(city);
        logger.info("ActionLog.getCollegesByLocation.start");
        return collegeMapper.mapEntityListToDtoList(collegeEntities);
    }
}
