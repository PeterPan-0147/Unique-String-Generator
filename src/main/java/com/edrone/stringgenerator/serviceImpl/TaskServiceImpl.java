package com.edrone.stringgenerator.serviceImpl;

import com.edrone.stringgenerator.exceptions.TaskNotFoundException;
import com.edrone.stringgenerator.model.Task;
import com.edrone.stringgenerator.model.UniqueString;
import com.edrone.stringgenerator.pojos.TaskDetails;
import com.edrone.stringgenerator.repository.TaskRepository;
import com.edrone.stringgenerator.repository.UniqueStringRepository;
import com.edrone.stringgenerator.response.ApiResponse;
import com.edrone.stringgenerator.response.TaskResponse;
import com.edrone.stringgenerator.service.TaskService;
import com.edrone.stringgenerator.utils.StringGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.edrone.stringgenerator.utils.StringGenerator.factorial;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UniqueStringRepository repository;

    ExecutorService pool = Executors.newCachedThreadPool();

    @Override
    public String generateStrings(TaskDetails details) {

        String chars = details.getUniqueCharacters();
        long charL = chars.length();
        long min = details.getMinLength();
        long max = details.getMaxLength();
        long amount = details.getAmount();
        if(charL>=max) {

            long combination = 0;
            for (long i = min; i <= max && combination < amount; i++) {
                combination += factorial(charL) / (factorial(charL - i));
            }
            if (combination >= amount) {
                String name = details.getUniqueCharacters() +"-"+ details.getMinLength() +"-"+ details.getMaxLength() +"-"+ details.getAmount();
                StringGenerator stringGenerator = new StringGenerator(taskRepository, repository);
                stringGenerator.setDetails(details);
                pool.execute(stringGenerator);
                return "sucessfully created task with name " + name;
            }
        }
        return "error";
    }

    @Override
    public ApiResponse<List<Task>> allRunningTask() {
        List<Task> tasks = taskRepository.findTasksByCheckedFalse();
        return new ApiResponse<>("success", LocalDateTime.now(), tasks);
    }

    @Override
    public TaskResponse getTaskResult(long task_id) {
        Set<UniqueString> stringSet;
        Set<String> newSet = new HashSet<>();
        Optional<Task> task = taskRepository.findById(task_id);
        if (task.isPresent()){
            stringSet = repository.findByTask(task);
            for (UniqueString str : stringSet) {
                newSet.add(str.getUniqueString());
            }
            return new TaskResponse("success", LocalDateTime.now(),task_id, newSet);
        }else{
            throw new TaskNotFoundException(String.format("Task with Id %d not found", task_id));
        }
    }
}
