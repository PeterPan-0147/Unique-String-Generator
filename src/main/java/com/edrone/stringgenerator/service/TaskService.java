package com.edrone.stringgenerator.service;

import com.edrone.stringgenerator.model.Task;
import com.edrone.stringgenerator.pojos.TaskDetails;
import com.edrone.stringgenerator.response.ApiResponse;
import com.edrone.stringgenerator.response.TaskResponse;

import java.util.List;

public interface TaskService {

    String generateStrings(TaskDetails details);

    ApiResponse<List<Task>> allRunningTask();

    TaskResponse getTaskResult(long task_id);

}
