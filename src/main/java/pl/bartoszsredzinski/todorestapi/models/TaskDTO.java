package pl.bartoszsredzinski.todorestapi.models;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private String date;
    private String description;
    private String status;
}
