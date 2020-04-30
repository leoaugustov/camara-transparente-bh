package camaratransparente.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import camaratransparente.servico.ServicoVereador;

@RestController
@RequestMapping("/vereadores")
public class ControllerVereadores {

	@Autowired
	private ServicoVereador servicoVereador;
	

	
	@GetMapping
	public ResponseEntity<?> listar() {
		return new ResponseEntity<>(servicoVereador.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<?> pegarFoto(@PathVariable("id") Long id) {
		HttpHeaders cabecalhos = new HttpHeaders();
		cabecalhos.setCacheControl(CacheControl.maxAge(12, TimeUnit.HOURS));
		
		return ResponseEntity.ok()
				.headers(cabecalhos)
				.body(servicoVereador.buscarFoto(id));
	}
	
}