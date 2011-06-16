/**
 * Classe que implementa tipos de item derivados de Papel.
 * @author Al�xis Kiosia (7152097)
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
	 * Construtor que recebe todos os atributos por par�metro.
	 * @param code Objeto do tipo String que representa o c�digo do item.
	 * @param name Objeto do tipo String que representa o nome do item.
	 * @param unit Objeto do tipo String que representa a unidade de medida do item.
	 * @param price Vari�vel do tipo double que representa o pre�o do item.
	 * @param qtt Vari�vel do tipo int que representa a quantidade do item em estoque.
	 */
	public Paper(String code, String name, String unit, double price, int qtt) {
		super(code, name, unit, price, qtt);
	}

}