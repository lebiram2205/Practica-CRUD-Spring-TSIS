package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.servicios.AlumnoController;

@Slf4j
@Service
public class AlumnoService {
	@Autowired
	private AlumnoRepository alumnoRepository;

	/**
	 * Crea un nuevo alumno
	 * 
	 * @param nuevoAlumno
	 * @return
	 */
	public Alumno create(Alumno nuevoAlumno) {
		// regla de negocio no se puede crear mas de un alumno con las misma matricula
		log.info("entre a alumno service" + nuevoAlumno);
		Optional<Alumno> alumno = alumnoRepository.findById(nuevoAlumno.getMatricula());
		// si no esta presente el alumno
		if (!alumno.isPresent()) {
			return alumnoRepository.save(nuevoAlumno);
		} else {
			return null;
		}
	}

	/**
	 * Regresa lista de alimnos
	 * 
	 * @return
	 */
	public Iterable<Alumno> retrieveAll() {
		return alumnoRepository.findAll();
	}

	/**
	 * Regresa el alumno de determinada matricula
	 * 
	 * @param matricula
	 * @return
	 */
	public Optional<Alumno> retrieve(Integer matricula) {
		return alumnoRepository.findById(matricula);

	}

	/**
	 * Actualiza un alumno de determinada matricula
	 * 
	 * @param matricula
	 * @param alumnoActualizar
	 * @return
	 */
	public Alumno udate(Integer matricula, Alumno alumnoActualizar) {
		Optional<Alumno> alumno = alumnoRepository.findById(matricula);
		if (!alumno.isPresent()) {
			return alumnoRepository.save(alumnoActualizar);
		} else {
			return null;
		}
	}

	/**
	 * Elimina un alumno de determinada matricula
	 * 
	 * @param matricula
	 * @return
	 */
//	public Alumno delete(Integer matricula) {
//		log.info("Buscando al alumno con matricula para eliminarlo desde service" + matricula);
//		return alumnoRepository.deleteById(matricula);
//	}
	public boolean delete(Integer matricula) {
		alumnoRepository.deleteById(matricula);
		Optional<Alumno> alumno = alumnoRepository.findById(matricula);
		return alumno.isPresent();
	}
}// fin de AlumnoService