package az.pashabank.ls.mscollege.service;

import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;

import java.util.List;

public interface CollegeService {

    CollegeDto getCollegeById(Long id);

    void createCollege(CollegeRequest collegeRequest);

    void deleteCollege(Long id);

    void updateCollege(Long id, CollegeRequest collegeRequest);

    List<CollegeDto> getCollegesByLocation(String city);
}
