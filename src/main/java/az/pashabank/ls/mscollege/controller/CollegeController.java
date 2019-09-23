package az.pashabank.ls.mscollege.controller;

import az.pashabank.ls.mscollege.model.CollegeRequest;
import az.pashabank.ls.mscollege.model.dto.CollegeDto;
import az.pashabank.ls.mscollege.service.CollegeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    private CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("{id}")
    public CollegeDto getCollegeById(@PathVariable Long id) {
        return collegeService.getCollegeById(id);
    }

    @PostMapping
    public CollegeDto createCollege(@RequestBody CollegeRequest collegeRequest) {
        return collegeService.createCollege(collegeRequest);
    }

    @PutMapping("{id}")
    public CollegeDto updateCollege(@PathVariable Long id, @RequestBody CollegeRequest collegeRequest) {
        return collegeService.updateCollege(id, collegeRequest);
    }

    @DeleteMapping
    public CollegeDto deleteCollege(@RequestBody CollegeRequest collegeRequest) {
        return collegeService.deleteCollege(collegeRequest);
    }
}
