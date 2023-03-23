package Modelo;

import AñoPasado.packModelo.Arma;
import AñoPasado.packModelo.Bomba;
import AñoPasado.packModelo.Escudo;
import AñoPasado.packModelo.Misil;
import AñoPasado.packModelo.Radar;

public class PokemonFactory {

	private static PokemonFactory mArmaFactory;

	private PokemonFactory() {
		
	}

	public static PokemonFactory getMiArmaFactory() {
		if(mArmaFactory==null) {
			mArmaFactory = new PokemonFactory();
		}
		return mArmaFactory;
	}
	
	public Pokemon createPokemon(String pTipo) {
		if(pTipo=="Agua") {
			return new Agua();
		}
		else if(pTipo=="Planta") {
			return new Planta();
		}
		else if(pTipo=="Fuego") {
			return new Fuego();
		}
		else {
			return new Electrico();
		}
	}

}