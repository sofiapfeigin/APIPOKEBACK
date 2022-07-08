package ar.com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.com.business.IPokemonBusiness;
import ar.com.business.exception.BusinessException;
import ar.com.business.exception.NotFoundException;
import ar.com.model.Pokemon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = Constantes.URL_API)

@Api(value = "Ordenes", description = "Operaciones relacionadas con las ordenes", tags = { "Ordenes" })

public class PokemonRestController {
	

	@Autowired
	private IPokemonBusiness pokemonBusiness;
	@ApiOperation(value = "Comienzo de carga de una orden con datos de sistemas externos", response = Pokemon.class)


	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "Orden no encontrada"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })

	@GetMapping(value = "/load/{nroPok}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pokemon> load(
			@ApiParam(value = "Id del pokemon que desea obtener") @PathVariable("nroPok") int nroPok) {

		try {
			return new ResponseEntity<Pokemon>(pokemonBusiness.load(nroPok),HttpStatus.OK);

		} catch (BusinessException e) {
			return new ResponseEntity<Pokemon>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Obtener listado de pokemones", response = Object.class)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pokemon>> list() {
		try {

			return new ResponseEntity<List<Pokemon>>(pokemonBusiness.list(),HttpStatus.OK);

		} catch (BusinessException e) {
			return new ResponseEntity<List<Pokemon>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
