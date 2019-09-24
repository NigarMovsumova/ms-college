package az.pashabank.ls.mscollege.controller;

import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colleges")
@Api(value="College Controller")
public class CollegeController {

    private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);
    private CollegeService collegeService;


    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("{id}")
    @ApiOperation("get college by id")
    public CollegeDto getCollegeById(@PathVariable Long id) {
        logger.debug("getCollegeById {} ", id);
        return collegeService.getCollegeById(id);
    }

    @GetMapping("/in")
    @ApiOperation("get colleges list by location")
    public List<CollegeDto> getCollegesByLocation(@RequestBody String city) {
        logger.debug("getCollegesByLocation{} ", city);
        return collegeService.getCollegesByLocation(city);
    }

    @PostMapping
    @ApiOperation("create college")
    public CollegeDto createCollege(@RequestBody CollegeRequest collegeRequest) {
        logger.debug("createCollege with name {} in {}", collegeRequest.getName(), collegeRequest.getCity());
        return collegeService.createCollege(collegeRequest);
    }

    @PutMapping("{id}")
    @ApiOperation("update college details")
    public CollegeDto updateCollege(@PathVariable Long id, @RequestBody CollegeRequest collegeRequest) {
        logger.debug("updateCollege with id {} ", id);
        return collegeService.updateCollege(id, collegeRequest);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete college by college")
    public CollegeDto deleteCollege(@PathVariable Long id) {
        logger.debug("deleteCollege with id {} ", id);
        return collegeService.deleteCollege(id);
    }
}
