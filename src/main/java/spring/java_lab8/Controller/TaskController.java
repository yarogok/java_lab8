package spring.java_lab8.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.java_lab8.Model.Task;
import spring.java_lab8.Repository.TaskRepository;

import java.util.List;

@Controller
@RequestMapping("/view-tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String showTasks(Model model) {
        List<Task> taskList = taskRepository.findAll();
        model.addAttribute("taskList", taskList);
        return "view-tasks";
    }

    @RequestMapping(value = "/{taskId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateTaskStatus(@PathVariable String taskId, Model model) {
        try {
            Long taskIdLong = Long.parseLong(taskId);
            Task task = taskRepository.findById(taskIdLong).orElse(null);

            if (task != null) {
                task.setCompleted(!task.isCompleted());
                taskRepository.save(task);
                return "redirect:/view-tasks";
            } else {
                List<Task> taskList = taskRepository.findAll();
                model.addAttribute("taskList", taskList);
                model.addAttribute("error", "Завдання не знайдено");
                return "view-tasks";
            }
        } catch (NumberFormatException e) {
            List<Task> taskList = taskRepository.findAll();
            model.addAttribute("taskList", taskList);
            model.addAttribute("error", "Неправильний формат подання даних. Надайте цілочислове значення");
            return "view-tasks";
        }
    }

    @GetMapping("/add-task")
    public String showAddTaskForm(Model model) {
        model.addAttribute("newTask", new Task());
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addTask(@ModelAttribute("newTask") Task newTask) {
        taskRepository.save(newTask);
        return "redirect:/view-tasks";
    }

    @GetMapping("/delete-task")
    public String showDeleteTaskForm(Model model) {
        List<Task> taskList = taskRepository.findAll();
        model.addAttribute("taskList", taskList);
        return "delete-task";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam Long taskId, Model model) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return "redirect:/view-tasks";
        } else {
            List<Task> taskList = taskRepository.findAll();
            model.addAttribute("error", "Завдання з вказаним ID не знайдено");
            model.addAttribute("taskList", taskList);
            return "delete-task";
        }
    }

    @GetMapping("/edit-task/{taskId}")
    public String showEditTaskForm(@PathVariable String taskId, Model model) {
        try {
            Long taskIdLong = Long.parseLong(taskId);
            Task task = taskRepository.findById(taskIdLong).orElse(null);

            if (task != null) {
                model.addAttribute("task", task);
                return "edit-task";
            } else {
                List<Task> taskList = taskRepository.findAll();
                model.addAttribute("taskList", taskList);
                model.addAttribute("error", "Завдання не знайдено");
                return "redirect:/view-tasks";
            }
        } catch (NumberFormatException e) {
            List<Task> taskList = taskRepository.findAll();
            model.addAttribute("taskList", taskList);
            model.addAttribute("error", "Неправильний формат подання даних. Надайте цілочислове значення");
            return "redirect:/view-tasks";
        }
    }

    @PostMapping("/edit-task")
    public String editTask(@ModelAttribute("task") Task editedTask, Model model) {
        taskRepository.save(editedTask);
        return "redirect:/view-tasks";
    }

}
