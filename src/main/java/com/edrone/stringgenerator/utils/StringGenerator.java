package com.edrone.stringgenerator.utils;


import com.edrone.stringgenerator.model.Task;
import com.edrone.stringgenerator.model.UniqueString;
import com.edrone.stringgenerator.pojos.TaskDetails;
import com.edrone.stringgenerator.repository.TaskRepository;
import com.edrone.stringgenerator.repository.UniqueStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StringGenerator implements Runnable {
    private TaskDetails details = new TaskDetails();
    private final TaskRepository taskRepository;
    private final UniqueStringRepository uniqueStringRepository;
    @Autowired
    public StringGenerator(TaskRepository taskRepository, UniqueStringRepository uniqueStringRepository){
        this.taskRepository = taskRepository;
        this.uniqueStringRepository = uniqueStringRepository;
    }
    public void setDetails(TaskDetails details){
        this.details = details;
    }
    public void generate(){
        Set<UniqueString> result = new HashSet<>();
        long min = details.getMinLength();
        long max = details.getMaxLength();
        long amount = details.getAmount();
        String chars = details.getUniqueCharacters();
        int charL = chars.length();
        long i = min;
        long combination =0;
        String name = details.getUniqueCharacters()+"-"+details.getMinLength()+"-"+details.getMaxLength()+"-"+details.getAmount();
        Task task = new Task(name);
        task.setChecked(true);
        task = taskRepository.save(task);
        while(i<= max && result.size()<amount) {
            combination += factorial(charL) / (factorial(charL - i));
            while(result.size() < combination && result.size() < amount) {
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                List<Integer> used = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    int index = random.nextInt(charL);
                    if(used.stream().noneMatch(o->o.equals(index))) {
                        used.add(index);
                        sb.append(chars.charAt(index));
                    }
                }
                UniqueString toAdd = new UniqueString(sb.toString(), task);
                result.add(toAdd);
            }
            i++;
        }
//        Set<UniqueString> saving = new HashSet<>();
//        for(String str: result){
//            saving.add(new UniqueString(str, task));
//        }
        uniqueStringRepository.saveAll(result);
        task.setChecked(false);
        taskRepository.save(task);
        System.out.println("done");
    }

    public static long factorial(long num){
        if(num>0){
            long mul = num;
            num--;
            while(num>0){
                mul*=num;
                num--;
            }
            return mul;
        }
        return 1;
    }

    @Override
    public void run() {
            generate();
    }
}
