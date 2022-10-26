package com.edrone.stringgenerator.controllers;

import com.edrone.stringgenerator.model.Task;
import com.edrone.stringgenerator.model.UniqueString;
import com.edrone.stringgenerator.pojos.TaskDetails;
import com.edrone.stringgenerator.response.ApiResponse;
import com.edrone.stringgenerator.response.TaskResponse;
import com.edrone.stringgenerator.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/generate")
    public String generate(@RequestBody TaskDetails details){
        return taskService.generateStrings(details);
    }

    @GetMapping("/get-all-processed-tasks")
    public ResponseEntity<ApiResponse<List<Task>>> getAllProcessedTasks(){
        return  new ResponseEntity<>(taskService.allRunningTask(), HttpStatus.OK);
    }

    @GetMapping("/get-task/{taskId}")
    public ResponseEntity<TaskResponse> getTaskResult(@PathVariable ("taskId") Long taskId){
        return new ResponseEntity<>(taskService.getTaskResult(taskId), HttpStatus.OK);
    }
}
