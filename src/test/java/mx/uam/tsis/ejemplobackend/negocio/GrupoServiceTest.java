package mx.uam.tsis.ejemplobackend.negocio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@ExtendWith(MockitoExtension.class)

public class GrupoServiceTest {
	@Mock
	private GrupoRepository gruporepositoryMock;
	@Mock
	private AlumnoService alumnoserviceMock;
	@InjectMocks
	private GrupoService gruposerviceMock;
	
	//Prueba unitaria para agregar un estudiante a un grupo
	@Test
	public void testSuccesfulAddStudentToGroup() {
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("MMMT3");
		
		 Alumno alumno= new Alumno();
		 alumno.setCarrera("Computacion");
		 alumno.setMatricula(2143);
		 alumno.setNombre("Mary");
	
		 
		//Stubbing para alumno repository
		 when(alumnoserviceMock.retrieve(2143)).thenReturn(alumno);
		 //Stubbing para grupo repository
		 when(gruporepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		 
		 boolean result = gruposerviceMock.addStudentToGrup(1, 2143);
		 
		 assertEquals(true, result);
		 //assertEquals(grupo.getAlumnos().get(0),alumno);
		
	}
	//
	@Test
	public void testUnSuccesfulAddStudentToGroup() {
	
		 Alumno alumno= new Alumno();
		 alumno.setCarrera("Computacion");
		 alumno.setMatricula(2143);
		 alumno.setNombre("Mary");
	
		 
		//Stubbing para alumno repository
		 when(alumnoserviceMock.retrieve(2143)).thenReturn(alumno);
		 //Stubbing para grupo repository
		 when(gruporepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable (null));
		 
		 boolean result = gruposerviceMock.addStudentToGrup(1, 2143);
		 
		 assertEquals(false, result);
		 
		
	}
	//prueba unitaria para eliminar un grupo
	@Test
	public void testSuccesfulDeletGroup() {
	
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("MMMT3");
	
		 
		//Stubbing para grupo repository
		 when(gruporepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		 
		 boolean result = gruposerviceMock.delete(1);
		 
		 assertEquals(true, result);
		 
		
	}
	
	//prueba unitaria para actualizar un grupo
	@Test
	public void testSuccesfulUpdateGroup() {
	
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("MMMT3");
		
		//Stubbing para grupo repository
		 when(gruporepositoryMock.findById(grupo.getId())).thenReturn(Optional.ofNullable(null));
		 when (gruporepositoryMock.save(grupo)).thenReturn(grupo);
		 grupo = gruposerviceMock.update(grupo.getId(), grupo);
		 
		 assertNotNull(grupo);
		
	}
	

}
