package Modelo;

import A�oPasado.packModelo.Arma;
import A�oPasado.packModelo.Bomba;
import A�oPasado.packModelo.Escudo;
import A�oPasado.packModelo.Misil;
import A�oPasado.packModelo.Radar;

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