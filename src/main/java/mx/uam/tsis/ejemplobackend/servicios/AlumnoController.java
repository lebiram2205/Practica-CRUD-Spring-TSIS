package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 * 
 * @author Maribel Contreras
 *
 */
@RestController
@Slf4j

public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;

	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid Alumno nuevoAlumno) {

		// No se deben agregar dos alumnos con la misma matricula

		log.info("Recibí llamada a create con " + nuevoAlumno);
		Alumno alumno = alumnoService.create(nuevoAlumno);
		if (alumno != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno repetido");
		}

	}

	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieveAll() {

		List<Alumno> alumno = alumnoService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(alumno);

	}

	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieve(@PathVariable("matricula") @Valid Integer matricula) {

		Alumno alumno = alumnoService.retrieve(matricula);
		if (alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PutMapping(path = "/alumnos/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@PathVariable @Valid int matricula, @RequestBody @Valid Alumno alumno) {
		// No se deben agregar dos alumnos con la misma matricula
		alumnoService.udate(matricula, alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumno);

	}

	@DeleteMapping(path = "/alumnos/{matricula}")
	public ResponseEntity<?> delete(@PathVariable("matricula") @Valid Integer matricula) {
		log.info("Buscando al alumno con matricula para eliminarlo" + matricula);
		Alumno alumno = alumnoService.retrieve(matricula);
		if (alumno != null) {
			alumnoService.delete(matricula);
			return ResponseEntity.status(HttpStatus.OK).body(matricula);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
