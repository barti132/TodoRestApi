package pl.bartoszsredzinski.todorestapi.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.todorestapi.models.Task;
import pl.bartoszsredzinski.todorestapi.models.TaskDTO;
import pl.bartoszsredzinski.todorestapi.services.TaskService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/task/")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("getByDate/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskByDate(@PathVariable String date){
        log.info("getByDate: " + date);
        return taskService.findByDate(date);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable String id) {
        log.info("getById: " + id);
        return taskService.findById(Long.parseLong(id));
    }

    @GetMapping("getByStatus/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskByStatus(@PathVariable String status) {
        log.info("getByStatus: " + status);
        return taskService.findByStatus(status);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTask() {
        log.info("getAll");
        return taskService.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO task) {
        log.info("Added new task; Body structure: " + task.getDate() + " " + task.getStatus() + " " + task.getDescription());
        return taskService.save(task);
    }

    @PutMapping("updateById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskById(@PathVariable String id, @RequestBody TaskDTO task) {
        log.info("updateById id: " + id);
        taskService.updateById(Long.parseLong(id), task);
    }

    @DeleteMapping("deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskById(@PathVariable String id) {
        log.info("deleteById: " + id);
        taskService.deleteById(Long.parseLong(id));
    }
}
