package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Service
@Slf4j
public class GrupoService {
	@Autowired
	private GrupoRepository gruporepository;
	@Autowired
	private AlumnoService alumnoservice;
	
	public Grupo create(Grupo nuevo) {
		//en dado caso validad reglas de negocio si es que existe
		return gruporepository.save(nuevo);
		
		
	}
	public Iterable <Grupo> retriveAll(){
		return gruporepository.findAll();
	}
	//agregar un alumno al grupo
	//metodo que conecte los objetos
	//son dos llaves primarias
	public boolean addStudentToGrup(Integer groupId, Integer matricula) {
		log.info("Agregando alumno con matricula"+matricula+"grupo" + groupId);
		//1.-Recuperamos primero al alumno
		Alumno alumno=alumnoservice.retrieve(matricula);
		
		//2.-Recuperamos el grupo
		Optional <Grupo> grupoOp= gruporepository.findById(groupId);
		
		//si grupo o matricula no estan presentes o no se encuentran
		//3.-Verificamos que el grupo y el alumno exitan
		if(!grupoOp.isPresent()|| alumno==null) {
			
			return false;
			
		}
		//conectar el grupo con el alumno
		//4.-Agrego el alumno al grupo
		Grupo  grupo =grupoOp.get();
		grupo.addAlumno(alumno);
		
		
		//hay que persistir ek cambio
		//5.-Persistir el cambio
		gruporepository.save(grupo);
		return true;
	}
	
	public Grupo update(Integer id, Grupo grupoActualizar) {
		Optional<Grupo> grupo = gruporepository.findById(id);
		if (!grupo.isPresent()) {
			return gruporepository.save(grupoActualizar);
		} else {
			return null;
		}
	}

	/**
	 * Elimina un grupo de determinado por id
	 * 
	 * @param id Identificador del grupo
	 * @return
	 */

	public boolean delete(Integer id) {
		gruporepository.deleteById(id);
		Optional<Grupo> grupo = gruporepository.findById(id);
		return grupo.isPresent();
	}

	
	
	

}
