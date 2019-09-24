package az.pashabank.ls.mscollege.controller;

import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);
    private CollegeService collegeService;


    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("{id}")
    public CollegeDto getCollegeById(@PathVariable Long id) {
        logger.debug("getCollegeById{} ", id);
        return collegeService.getCollegeById(id);
    }

    @GetMapping("/in/{city}")
    public List<CollegeDto> getCollegesByLocation(@PathVariable String city) {
        logger.debug("getCollegesByLocation{} ", city);
        return collegeService.getCollegesByLocation(city);
    }

    @PostMapping
    public CollegeDto createCollege(@RequestBody CollegeRequest collegeRequest) {
        logger.debug("createCollege with name {} in {}", collegeRequest.getName(), collegeRequest.getCity());
        return collegeService.createCollege(collegeRequest);
    }

    @PutMapping("{id}")
    public CollegeDto updateCollege(@PathVariable Long id, @RequestBody CollegeRequest collegeRequest) {
        logger.debug("updateCollege with id {} ", id);
        return collegeService.updateCollege(id, collegeRequest);
    }

    @DeleteMapping
    public CollegeDto deleteCollege(@RequestBody CollegeRequest collegeRequest) {
        logger.debug("deleteCollege {} in {} ", collegeRequest.getName(), collegeRequest.getCity());
        return collegeService.deleteCollege(collegeRequest);
    }
}
