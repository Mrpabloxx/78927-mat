package mx.uv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@RequestMapping("/")
	public String name() {
		return "<h1>Hola mundo</h1>";
	}

	@RequestMapping("/adios")
	public String adios() {
		return "<h1>Adios mundo</h1>";
	}

	@RequestMapping("/productos")
	public List<String> productos() {
		List <String> p = new ArrayList<String>();
		p.add("Producto 1");
		p.add("Producto 2");
		p.add("Producto 3");
		p.add("Producto 4");
		return p;
	}

	@RequestMapping("/productos2")
	public List<Productos> Productos2() {
		List <Productos> lista = new ArrayList<Productos>();
		Productos p = new Productos("Tamales", 10);
		lista.add(p);
		Productos p1 = new Productos("Cafe", 10);
		lista.add(p1);
		Productos p2 = new Productos("Agua", 10);
		lista.add(p2);

		return lista;
	}



	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<String> saludarGet() {
		List <String> p = new ArrayList<String>();

		return p;
	}

	@PostMapping("/request")
	public ResponseEntity personas(
		@RequestBody Personas personas) {
		
		exampleService.fakeAuthenticate(personas);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	

	// @RequestMapping(value = "/put", method = RequestMethod.PUT)
	@PostMapping("/pu")
	public String saludarPut() {
		return "mensaje de tipo GET";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String saludarDelete() {
		return "mensaje de tipo GET";
	}
}
