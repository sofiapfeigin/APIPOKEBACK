package ar.com.business;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.springframework.stereotype.Service;

import ar.com.business.exception.BusinessException;
import ar.com.business.exception.NotFoundException;
import ar.com.model.Pokemon;

@Service
public class PokemonBusiness implements IPokemonBusiness {

	@Override
	public Pokemon load(int id) throws BusinessException, NotFoundException {
		
		Pokemon pok = null;

		try {
			URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);
			
			try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
				JsonObject obj = rdr.readObject();
				
				pok = new Pokemon();
				pok.setId(obj.getInt("id"));
				pok.setFoto("https://pokeapi.co/api/v2/pokemon/" + id);
				pok.setNombre(obj.getString("name"));
				pok.setPeso(obj.getInt("weight"));
				JsonArray habilidades = obj.getJsonArray("abilities");
				JsonObject hab = habilidades.getJsonObject(0);
				hab = hab.getJsonObject("ability");
				pok.setHabilidades(hab.getString("name"));
				JsonArray tipos = obj.getJsonArray("types");
				JsonObject tipo = tipos.getJsonObject(0);
				tipo = tipo.getJsonObject("type");
				pok.setTipo(tipo.getString("name"));
				
				return pok;
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Pokemon> list() throws BusinessException {
		ArrayList<Pokemon> p = new ArrayList<Pokemon>();

		try {
			URL url = new URL("https://pokeapi.co/api/v2/pokemon");
			try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
				JsonObject obj = rdr.readObject();
				JsonArray results = obj.getJsonArray("results");
				for (JsonObject result : results.getValuesAs(JsonObject.class)) {
					Pokemon pokemon = new Pokemon();
					url = new URL(result.getString("url"));
					try (InputStream is2 = url.openStream(); JsonReader rdr2 = Json.createReader(is2)) {

						JsonObject obj2 = rdr2.readObject();
						pokemon.setId(obj2.getInt("id"));
						pokemon.setFoto(result.getString("url"));
						pokemon.setNombre(result.getString("name"));
						pokemon.setPeso(obj2.getInt("weight"));
						JsonArray habilidades = obj2.getJsonArray("abilities");
						JsonObject hab = habilidades.getJsonObject(0);
						hab = hab.getJsonObject("ability");
						pokemon.setHabilidades(hab.getString("name"));
						JsonArray tipos = obj2.getJsonArray("types");
						JsonObject tipo = tipos.getJsonObject(0);
						tipo = tipo.getJsonObject("type");
						pokemon.setTipo(tipo.getString("name"));
						p.add(pokemon);

					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new BusinessException(e);
		}

		return p;
	}

}
