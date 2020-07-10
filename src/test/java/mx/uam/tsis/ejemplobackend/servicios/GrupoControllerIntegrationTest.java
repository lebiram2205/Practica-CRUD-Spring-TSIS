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
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrupoControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private AlumnoRepository alumnoRepository;

	@BeforeEach
	public void prepare() {

		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD

	}
	/**
	 * Prueba de integracion para añadir un alumno a un grupo con exito
	 */
	@Test
	public void TestAddStudentToGroup200() {

		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("MMMT3");

		grupoRepository.save(grupo);
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		alumnoRepository.save(alumno);

		// encabezado de la invocacion
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());

		HttpEntity<Grupo> request = new HttpEntity<>(grupo, headers);

		ResponseEntity<Grupo> responseEntity = restTemplate.exchange("/grupos/1/alumnos?matricula=12345678",
				HttpMethod.POST, request, Grupo.class);

		// el endpoint regresa el estatus 200 si es correcto
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	/**
	 *Prueba de integracion añadiendo un alumno a un grupo no exitoso 
	 */
	@Test
	public void TestUnAddStudentToGroup404() {

		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("MMMT3");

		grupoRepository.save(grupo);
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		alumnoRepository.save(alumno);

		// encabezado de la invocacion
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());

		HttpEntity<Grupo> request = new HttpEntity<>(grupo, headers);

		ResponseEntity<Grupo> responseEntity = restTemplate.exchange("/grupos/2/alumnos?matricula=12345678",
				HttpMethod.POST, request, Grupo.class);

		// el endpoint regresa el estatus 200 si es correcto
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

}
