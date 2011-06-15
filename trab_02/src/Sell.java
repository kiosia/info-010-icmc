/**
 * Classe que implementa a transa��o de compra.
 * @author Al�xis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Sell extends Transaction {

	/**
	 * Construtor vazio.
	 */
	public Sell(){

	}
	
	/**
	 * Construtor que recebe todos os atributos por par�metro.
	 * @param username Objeto do tipo String que representa o username do usu�rio que realizou a venda.
	 * @param itemCode Objeto do tipo String que representa o c�digo do item que foi vendido.
	 * @param qtt Vari�vel do tipo int que representa quantidade que foi vendida.
	 */
	public Sell(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}

	/**
	 * M�todo que executa a compra.
	 */
	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt-this.qtt);
		vTransactions.add(this);
	}

	/**
	 * M�todo que imprime a transa��o de compra.
	 */	
	@Override
	public void printTransaction() {
		System.out.println(this.username+" vendeu "+this.qtt+" unidades do produto de codigo "+this.itemCode);	
	}
}