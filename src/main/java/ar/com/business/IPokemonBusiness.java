package ar.com.business;

import java.util.List;

import ar.com.business.exception.BusinessException;
import ar.com.business.exception.NotFoundException;
import ar.com.model.Pokemon;


public interface IPokemonBusiness {
	
	public Pokemon load(int id) throws BusinessException, NotFoundException;
	public List<Pokemon> list() throws BusinessException;

}
