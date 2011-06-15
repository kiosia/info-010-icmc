/**
 * Classe que implementa a transação de compra.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Sell extends Transaction {

	/**
	 * Construtor vazio.
	 */
	public Sell(){

	}
	
	/**
	 * Construtor que recebe todos os atributos por parâmetro.
	 * @param username Objeto do tipo String que representa o username do usuário que realizou a venda.
	 * @param itemCode Objeto do tipo String que representa o código do item que foi vendido.
	 * @param qtt Variável do tipo int que representa quantidade que foi vendida.
	 */
	public Sell(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}

	/**
	 * Método que executa a compra.
	 */
	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt-this.qtt);
		vTransactions.add(this);
	}

	/**
	 * Método que imprime a transação de compra.
	 */	
	@Override
	public void printTransaction() {
		System.out.println(this.username+" vendeu "+this.qtt+" unidades do produto de codigo "+this.itemCode);	
	}
}