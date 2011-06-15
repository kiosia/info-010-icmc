/**
 * Classe que implementa a transa��o de compra.
 * @author Al�xis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Buy extends Transaction {

	public Buy(){

	}
	
	public Buy(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}
	
	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt+this.qtt);
		vTransactions.add(this);
	}

	@Override
	public void printTransaction() {
		System.out.println(this.username+" comprou "+this.qtt+" unidades do produto de codigo "+this.itemCode);
		
	}

}

// TODO comentar a classe Buy