package com.humanbooster.Examen.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Project {
    private Long id;
    private String name;
    private User creator;
    private List<Task> tasks = new ArrayList<>();
}
