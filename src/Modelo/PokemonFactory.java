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
	
	public Pokemon createPokemon(int i, String pNombre, String nombreJug) {
		if(i==1) {
			return new Planta(pNombre,nombreJug);
		}
		else if(i==2) {
			return new Agua(pNombre,nombreJug);
		}
		else if(i==3) {
			return new Electrico(pNombre,nombreJug);
		}
		else {
			return new Fuego(pNombre,nombreJug);
		}
		
	}//

}