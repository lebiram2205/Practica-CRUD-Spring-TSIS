package mx.uam.tsis.ejemplobackend.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;



public interface GrupoRepository extends CrudRepository <Grupo, Integer>{//tipo de la llave primaria entero
	//crudRepository me dara las opciones de creacion, recuperacion, eliminacion etc.
	

}
