package pl.bartoszsredzinski.todorestapi.controllers.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task/")
public class TaskRestController {

    @GetMapping("getByDate/{date}")
    public String getTaskByDate(@PathVariable String date) {
        return "";
    }

    @GetMapping("getById/{id}")
    public String getTaskById(@PathVariable String id) {
        return "";
    }

    @GetMapping("getByStatus/{status}")
    public String getTaskByStatus(@PathVariable String status) {
        return "";
    }

    @GetMapping("getAll")
    public String getAllTask() {
        return "";
    }

    @PostMapping("add")
    public String addTask() {
        return "";
    }

    @PutMapping("updateById/{id}")
    public String updateTaskById(@PathVariable String id) {
        return "";
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteTaskById(@PathVariable String id) {
        return "";
    }

}
