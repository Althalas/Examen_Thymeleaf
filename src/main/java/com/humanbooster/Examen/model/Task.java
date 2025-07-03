package com.humanbooster.Examen.model;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private TaskStatus status;
    private User assignee;
}
