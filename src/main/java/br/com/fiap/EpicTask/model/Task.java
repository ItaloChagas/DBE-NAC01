package br.com.fiap.EpicTask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity(name = "tasks")
public class Task {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O título da task é obrigatório")
	private String title; 
	
	@NotBlank (message = "O título da task é obrigatório")
	@Size(max = 140, message = "A descrição precisa ser mais objetiva")
	private String description;
	
	//@Length(min= 1, max = 100, message = "Pontuação precisar ser entre 1 e 100")	
	private int point;
	
	//@Length(min= 1, max = 100, message = "Status precisar ser entre 1 e 100")
	private int status;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	} 
	
	
	
}
