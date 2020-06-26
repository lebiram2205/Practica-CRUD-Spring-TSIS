package mx.uam.tsis.ejemplobackend.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.servicios.AlumnoController;

@Component
@Slf4j
public class AlumnoRepository {

	// La "base de datos"

	private Map<Integer, Alumno> alumnoRepository = new HashMap<>();

	/**
	 * Guarda los datos en la base de datos
	 * 
	 * @param nuevoAlumno
	 */
	public Alumno save(Alumno nuevoAlumno) {
		alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		return nuevoAlumno;
	}
	/**
	 * Busca un alumno en la base de datos por medio de su matricula
	 * @param matricula
	 * @return devuelve el alumno
	 */
	public Alumno findByMatricula(Integer matricula) {
		return alumnoRepository.get(matricula);
	}
	/**
	 * Busca todos los elementos guardados de la base de datos
	 * @return devuelve la lista de alumnos
	 */
	public List<Alumno> find() {
		return new ArrayList<>(alumnoRepository.values());
	}
	/**
	 * Actualiza un alumno en la base de datos
	 * @param matricula 
	 * @param alumno
	 * @return devuelve el alumno actualizado
	 */
	public Alumno put(Integer matricula, Alumno alumno) {
		return alumnoRepository.put(matricula, alumno);
	}
	/**
	 * Eliminar un alumno de la base de datos 
	 * @param matricula
	 * @return el alumno eliminado
	 */
	public Alumno deleteByMatricula(Integer matricula) {
		return alumnoRepository.remove(matricula);
	}

}// find e clase Alumno Repository
