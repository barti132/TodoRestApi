package pl.bartoszsredzinski.todorestapi.services;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.todorestapi.models.Task;
import pl.bartoszsredzinski.todorestapi.models.TaskDTO;
import pl.bartoszsredzinski.todorestapi.repositories.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<Task> findAll() {
        List<Task> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public List<Task> findByDate(String dateAsString) {
        SimpleDateFormat formatter;
        if(dateAsString.contains(".")){
            formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        }
        else {
            formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        }

        Date date = null;
        try {
            date = formatter.parse(dateAsString);
        }
        catch (ParseException e){
            e.getStackTrace();
        }


        List<Task> list = new ArrayList<>();
        repository.findByDate(date).forEach(list::add);
        return list;
    }

    public List<Task> findByStatus(String status) {
        List<Task> list = new ArrayList<>();
        repository.findByStatus(status).forEach(list::add);
        return list;
    }

    public Task findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Task task) {
        repository.save(task);
    }

    public ResponseEntity<Task> save(TaskDTO taskDTO) {
        Task task = repository.save(mapTaskDTOtoTask(taskDTO));

        if(task == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
    }

    public void delete(Task task) {
        repository.delete(task);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updateById(Long id, TaskDTO taskDTO) {
        Task task = mapTaskDTOtoTask(taskDTO);

        Task task1 = findById(id);
        if(task.getDate() != null){
            task1.setDate(task.getDate());
        }
        if(task.getDescription() != null){
            task1.setDescription(task.getDescription());
        }
        if(task.getStatus() != null){
            task1.setStatus(task.getStatus());
        }

        save(task1);
    }

    private Task mapTaskDTOtoTask(TaskDTO task){

        SimpleDateFormat formatter;
        if(task.getDate().contains(".")){
            formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        }
        else {
            formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        }

        try {
            Task task1 = new Task();
            if(task.getDate() != null){
                task1.setDate(formatter.parse(task.getDate()));
            }
            if(task.getDescription() != null){
                task1.setDescription(task.getDescription());
            }
            if(task.getStatus() != null){
                task1.setStatus(task.getStatus());
            }

            return task1;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
