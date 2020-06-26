package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;

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
		Alumno alumno = alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		if (alumno == null) {
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
	public List<Alumno> retrieveAll() {
		return alumnoRepository.find();
	}

	/**
	 * Regresa el alumno de determinada matricula
	 * 
	 * @param matricula
	 * @return
	 */
	public Alumno retrieve(Integer matricula) {
		return alumnoRepository.findByMatricula(matricula);
	}

	/**
	 * Actualiza un alumno de determinada matricula
	 * 
	 * @param matricula
	 * @param alumnoActualizar
	 * @return
	 */
	public Alumno udate(Integer matricula, Alumno alumnoActualizar) {
		Alumno alumno = alumnoRepository.findByMatricula(alumnoActualizar.getMatricula());
		if (alumno != null) {
			return alumnoRepository.put(matricula, alumnoActualizar);
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
	public Alumno delete(Integer matricula) {
		log.info("Buscando al alumno con matricula para eliminarlo desde service" + matricula);
		return alumnoRepository.deleteByMatricula(matricula);
	}

}// fin de AlumnoService