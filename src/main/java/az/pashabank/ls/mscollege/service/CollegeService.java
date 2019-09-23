package az.pashabank.ls.mscollege.service;

import az.pashabank.ls.mscollege.exception.NotFoundException;
import az.pashabank.ls.mscollege.mappers.CollegeMapper;
import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.model.entity.CollegeEntity;
import az.pashabank.ls.mscollege.repository.CollegeRepository;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {

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

        CollegeDto collegeDto = collegeMapper.mapEntityToDto(collegeEntity);
        return collegeDto;
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
            //TODO ADD LOGGING
        }
        return collegeMapper.mapEntityToDto(collegeEntity);
    }

    public CollegeDto deleteCollege(CollegeRequest collegeRequest) {
        CollegeEntity collegeEntity = collegeRepository
                .getCollegeEntityByNameAndCity(collegeRequest.getName(), collegeRequest.getCity());

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
}
