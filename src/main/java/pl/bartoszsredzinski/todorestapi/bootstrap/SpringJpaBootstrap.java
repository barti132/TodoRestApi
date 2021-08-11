package pl.bartoszsredzinski.todorestapi.bootstrap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.bartoszsredzinski.todorestapi.models.Task;
import pl.bartoszsredzinski.todorestapi.services.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


@Slf4j
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final TaskService taskService;

    public SpringJpaBootstrap(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadTasks();
    }

    private void loadTasks(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try {
            Task task1 = new Task().builder().date(formatter.parse("01-10-2000")).description("Przykładowy opis1").status("completed").build();
            Task task2 = new Task().builder().date(formatter.parse("01-11-2011")).description("Przykładowy opis2").status("in progress").build();
            Task task3 = new Task().builder().date(formatter.parse("01-12-2024")).description("Przykładowy opis3").status("planned").build();
            Task task4 = new Task().builder().date(formatter.parse("30-10-2020")).description("Przykładowy opis4").status("canceled").build();

            taskService.save(task1);
            taskService.save(task2);
            taskService.save(task3);
            taskService.save(task4);
        }
        catch (ParseException e){
            e.getStackTrace();
            log.error("Task not loaded!!!");
        }
        log.info("Task loaded");
    }
}
