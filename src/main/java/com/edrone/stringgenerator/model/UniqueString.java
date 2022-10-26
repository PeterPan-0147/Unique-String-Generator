package com.edrone.stringgenerator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniqueString {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueString;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    public UniqueString(String uniqueString, Task task){
        this.uniqueString = uniqueString;
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniqueString)) return false;
        UniqueString that = (UniqueString) o;
        return getUniqueString().equals(that.getUniqueString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniqueString());
    }
}
