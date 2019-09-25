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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colleges")
@Api(value = "College Controller")
public class CollegeController {

    private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);
    private CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("{id}")
    @ApiOperation("get college by id")
    public CollegeDto getCollegeById(@PathVariable(name="id") Long id) {
        logger.debug("Get college of id {} start", id);
        return collegeService.getCollegeById(id);
    }

    @PostMapping("/in")
    @ApiOperation("get colleges list by location")
    public List<CollegeDto> getCollegesByLocation(@RequestBody String city) {
        logger.debug("Get colleges of city {} start", city);
        return collegeService.getCollegesByLocation(city);
    }

    @PostMapping
    @ApiOperation("create college")
    public void createCollege(@RequestBody CollegeRequest collegeRequest) {
        logger.debug("Create college of name {} in city {} start", collegeRequest.getName(), collegeRequest.getCity());
        collegeService.createCollege(collegeRequest);
        logger.debug("Create college of name {} in city {} end", collegeRequest.getName(), collegeRequest.getCity());
    }

    @PutMapping("{id}")
    @ApiOperation("update college details")
    public void updateCollege(@PathVariable Long id, @RequestBody CollegeRequest collegeRequest) {
        logger.debug("updateCollege with id {} start ", id);
        collegeService.updateCollege(id, collegeRequest);
        logger.debug("updateCollege of id {} end", id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete college by college id")
    public void deleteCollege(@PathVariable Long id) {
        logger.debug("deleteCollege of id {} start", id);
        collegeService.deleteCollege(id);
        logger.debug("deleteCollege of id {} end", id);
    }
}
