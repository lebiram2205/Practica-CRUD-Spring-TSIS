package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.Optional;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {
	
 @Mock
  private AlumnoRepository alumnoRepositoryMock;//mocj generado por el Mockito
 @InjectMocks
 private AlumnoService alumnoService;

  @Test
  public void testSuccesfulCreate() {
	  Alumno alumno= new Alumno();
	  alumno.setCarrera("Computacion");
	  alumno.setMatricula(2143);
	  alumno.setNombre("Mary");
	  
	  //simula lo que aria el alumnorepositoryrest cuando le pasan una matricula que no ha sido guardada
	  when(alumnoRepositoryMock.findById(2143)).thenReturn(Optional.ofNullable(null));
	  when (alumnoRepositoryMock.save(alumno)).thenReturn(alumno);
	  //aqui ejecuto a la unidad que queiro porbar
	  alumno= alumnoService.create(alumno);
	  //aqui compruebo el resultado 
	  assertNotNull(alumno);//probar que la referencia no sea nula 
	  
  }
  @Test
  public void testUsuSuccesfulCreate() {
	  Alumno alumno= new Alumno();
	  alumno.setCarrera("Computacion");
	  alumno.setMatricula(2143);
	  alumno.setNombre("Mary");
	  
	  //simula lo que aria el alumnorepositoryrest cuando le pasan una matricula que no ha sido guardada
	  when(alumnoRepositoryMock.findById(2143)).thenReturn(Optional.ofNullable(alumno));
	  
	  //aqui ejecuto a la unidad que queiro porbar
	  alumno= alumnoService.create(alumno);
	  //aqui compruebo el resultado 
	  assertNull(alumno);//probar que la referencia no sea nula 
	  
  }
  
  
  
  
} 
