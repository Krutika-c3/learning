package com.application.springRest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.With;

@With
@Data
@AllArgsConstructor
public class Student {
    private Integer id;
    @NonNull
    private String name;
    private String dob;

    public Student(String name) {
        this.name = name;
    }
}
