/**
 * Classe que implementa a transação de venda.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Sell extends Transaction {

	public Sell(){

	}
	
	public Sell(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}

	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt-this.qtt);
		vTransactions.add(this);
	}

	@Override
	public void printTransaction() {
		System.out.println(this.username+" vendeu "+this.qtt+" unidades do produto de codigo "+this.itemCode);	
	}
}

// TODO comentar a classe Sell