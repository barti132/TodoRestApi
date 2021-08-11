package pl.bartoszsredzinski.todorestapi.services;


import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.todorestapi.models.Task;
import pl.bartoszsredzinski.todorestapi.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<Task> findAll(){
        List<Task> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public List<Task> findByDate(Date date){
        List<Task> list = new ArrayList<>();
        repository.findByDate(date).forEach(list::add);
        return list;
    }

    public List<Task> findByStatus(String status){
        List<Task> list = new ArrayList<>();
        repository.findByStatus(status).forEach(list::add);
        return list;
    }

    public Task findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Task save(Task task){
        return repository.save(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
