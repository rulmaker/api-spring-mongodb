package com.angular.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angular.app.model.Tarea;
import com.angular.app.repository.TareaRepository;

@CrossOrigin
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
//RequestMethod.PUT })
@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	private TareaRepository repo;
	
	@GetMapping("")
	List<Tarea> index(){
		return repo.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	Tarea create(@RequestBody Tarea tarea) {
		return repo.save(tarea);
	}
	
	@PutMapping("{id}")
	Tarea update(@PathVariable String id, @RequestBody Tarea tarea) {
		Tarea tareaFromDB = repo
				.findById(id)
				.orElseThrow(RuntimeException::new);

		tareaFromDB.setNombre(tarea.getNombre());
		tareaFromDB.setCompletada(tarea.isCompletada());
		
		return repo.save(tareaFromDB);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	void delete(@PathVariable String id) {
		Tarea tarea = repo
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		repo.delete(tarea);
	}
	
}
