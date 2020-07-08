package mx.uam.tsis.ejemplobackend.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlumnoControllerIntregationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@BeforeEach
	public void prepare() {
		
		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD
		
	}
	
	
	@Test
	public void testCreate201() {
		
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");

		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		HttpEntity <Alumno> request = new HttpEntity <> (alumno, headers);
		
		ResponseEntity <Alumno> responseEntity = restTemplate.exchange("/alumnos", HttpMethod.POST, request, Alumno.class);

		log.info("Me regresó:"+responseEntity.getBody());
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		Optional <Alumno> optAlumno = alumnoRepository.findById(12345678);
		assertEquals(alumno,optAlumno.get());
		
	}

	@Test
	public void testCreate400() {
		
	}

}
