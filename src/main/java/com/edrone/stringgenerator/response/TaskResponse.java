package com.edrone.stringgenerator.response;

import com.edrone.stringgenerator.model.UniqueString;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class TaskResponse {

    private String message;
    private LocalDateTime dateTime;
    private long taskId;
    private Set<String> uniqueStrings;
}
