package pl.bartoszsredzinski.todorestapi.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.todorestapi.models.Task;
import pl.bartoszsredzinski.todorestapi.models.TaskDTO;
import pl.bartoszsredzinski.todorestapi.services.TaskService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task/")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("getByDate/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskByDate(@PathVariable String date) throws ParseException {
        return taskService.findByDate(date);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable String id) {
        return taskService.findById(Long.parseLong(id));
    }

    @GetMapping("getByStatus/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskByStatus(@PathVariable String status) {
        return taskService.findByStatus(status);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTask() {
        return taskService.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO task) {
        return taskService.save(task);
    }

    @PutMapping("updateById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskById(@PathVariable String id, @RequestBody TaskDTO task) {
        taskService.updateById(Long.parseLong(id), task);
    }

    @DeleteMapping("deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskById(@PathVariable String id) {
        taskService.deleteById(Long.parseLong(id));
    }
}
