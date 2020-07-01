package mx.uam.tsis.ejemplobackend.datos;



import org.springframework.data.repository.CrudRepository;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;


public interface AlumnoRepository extends CrudRepository <Alumno, Integer>{

	
}// find de la interface