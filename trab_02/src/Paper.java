/**
 * Classe que implementa tipos de item derivados de Papel.
 * @author Aléxis Kiosia (7152097)
 * @since 15/06/2011
 */
public class Paper extends Item {

	/**
	 * Construtor vazio.
	 */
	public Paper(){
		System.out.println("Construtor de papel!");
	}
	
	/**
	 * Construtor que recebe todos os atributos por parâmetro.
	 * @param code Objeto do tipo String que representa o código do item.
	 * @param name Objeto do tipo String que representa o nome do item.
	 * @param unit Objeto do tipo String que representa a unidade de medida do item.
	 * @param price Variável do tipo double que representa o preço do item.
	 * @param qtt Variável do tipo int que representa a quantidade do item em estoque.
	 */
	public Paper(String code, String name, String unit, double price, int qtt) {
		super(code, name, unit, price, qtt);
	}

}