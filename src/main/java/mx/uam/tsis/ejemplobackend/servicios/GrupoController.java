package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.GrupoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@RestController
@RequestMapping
@Slf4j

public class GrupoController {
	
@Autowired
private GrupoService gruposervice;
@ApiOperation(
		value="Crea un grupo",
		notes="Permite crear un nuevo grupo"
		)//documentacion del api
@PostMapping(path="/grupos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> create(@RequestBody @Valid Grupo nuevoGrupo){
	Grupo grupo= gruposervice.create(nuevoGrupo);
	if(grupo !=null) {
		 return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrupo);
	}
	else {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo crear el alumno");
	}
	
}

@GetMapping(path="/grupos", produces = MediaType.APPLICATION_JSON_VALUE) 
public ResponseEntity<?> retrieveAll(){
	Iterable<Grupo> result = gruposervice.retriveAll();
	return ResponseEntity.status(HttpStatus.OK).body(result);
	
}
/**
 * Post grupos/{id}/alumno?matricula=1234
 * @param Id
 * @return
 */
@PostMapping(path="/grupos/{id}/alumnos",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> addStudentToGroup(@PathVariable("id") Integer id, 
										   @RequestParam("matricula")Integer matricula){
	boolean result= gruposervice.addStudentToGrup(id, matricula);
	if(result) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}

}
