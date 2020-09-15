package br.com.fiap.EpicTask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private MessageSource messageSource;

	
	@GetMapping()
	public ModelAndView task() {
		List<Task> tasks = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
		   	
	@RequestMapping("new")
	public String formNew(Task task) {
		return "task_new";
	}
	
	@PostMapping()
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) return "user_new";
		repository.save(task);
		redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
		return "redirect:/task/new";
	}
	
	
	@RequestMapping(value="/edit/{id}")    
    public ModelAndView edit(@PathVariable Long id, Model Task){    
        Task task = repository.getOne(id);        
        ModelAndView modelAndView = new ModelAndView("task_edit");
        modelAndView.addObject("task", task);
        return modelAndView;    
    }
	
	@PostMapping(value="/edit")    
    public String edit(@Valid Task task, BindingResult result, RedirectAttributes redirect){    
		if (result.hasErrors()) return "task_edit";		
        repository.save(task);        
        redirect.addFlashAttribute("message", "Tarefa alterada com sucesso.");
        return "redirect:/task/edit/" + task.getId();
        
    }
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id, RedirectAttributes redirect) {
		repository.deleteById(id);
		redirect.addFlashAttribute("message", getMessage("message.deletetask.success"));
		return "redirect:/task";
	}
	
	private String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
	
}
