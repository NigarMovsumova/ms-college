package az.pashabank.ls.mscollege.mappers;

import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.model.entity.CollegeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollegeMapper {
    public CollegeDto mapEntityToDto(CollegeEntity collegeEntity) {
        return CollegeDto.builder()
                .id(collegeEntity.getId())
                .name(collegeEntity.getName())
                .city(collegeEntity.getCity())
                .build();
    }

    public List<CollegeDto> mapEntityListToDtoList(List<CollegeEntity> collegeEntities) {
        return collegeEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
