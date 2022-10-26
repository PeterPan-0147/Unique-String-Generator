package com.edrone.stringgenerator.pojos;

import lombok.Data;

@Data
public class TaskDetails {

    private String uniqueCharacters;
    private int minLength;
    private int maxLength;
    private int amount;
}
