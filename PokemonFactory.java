package Modelo;


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
		else if (pTipo=="Normal") {
			return new Normal();
		}
		else {
			return new Electrico();
		}
	}

}