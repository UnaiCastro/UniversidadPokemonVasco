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
	
	public Pokemon createPokemon(String pTipo, String pNombre) {
		if(pTipo=="Agua") {
			return new Agua(pNombre);
		}
		else if(pTipo=="Planta") {
			return new Planta(pNombre);
		}
		else if(pTipo=="Fuego") {
			return new Fuego(pNombre);
		}
		else if (pTipo=="Normal") {
			return new Normal(pNombre);
		}
		else {
			return new Electrico(pNombre);
		}
	}

}