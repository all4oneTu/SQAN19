package com.example.demo.Controller;

import com.example.demo.Entity.ScheduleEntity;
import com.example.demo.Service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeTypeApi {
    private ScheduleService scheduleService;
    public ChangeTypeApi(ScheduleService scheduleService) {
        super();
        this.scheduleService = scheduleService;
    }

    @PostMapping("/changeType/{id}")
    // get id from params and find schedule by id
    // change type of schedule
    // return schedule
    public ResponseEntity<String> changeType(@PathVariable("id") Long id) {
        ScheduleEntity scheduleEntity = scheduleService.findById(id);
        if (scheduleEntity.getIsSubstitute() == "no"){
            scheduleEntity.setIsSubstitute("yes");
        } else {
            scheduleEntity.setIsSubstitute("no");
        }
        return ResponseEntity.ok("Change type successfully");
    }


}
