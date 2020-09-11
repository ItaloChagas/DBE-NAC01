package br.com.fiap.EpicTask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.TaskRepository;


@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskRepository repository;

	
	@GetMapping()
	public ModelAndView task() {
		List<Task> tasks = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
	
	@RequestMapping(value="/new/{id}")    
    public ModelAndView edit(@PathVariable Long id, Model Task){    
        Optional<Task> task = repository.findById(id);    
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("task", task);
        return modelAndView;    
    }    
}
