package com.ibm.pm.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@SpringBootApplication
public class PmCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmCoreApplication.class, args);
	}

}

@Entity
@Data
class Process {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String organization;

}

interface ProcessRepository extends JpaRepository<Process, Long> {

}

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ProcessResource {

	private final ProcessRepository processRepository;

	@GetMapping("/processes")
	public List<Process> getProcesses() {
		return processRepository.findAll();
	}

	@PostMapping("/processes")
	public Process createProcess(@RequestBody Process process) {
		return processRepository.save(process);
	}

}