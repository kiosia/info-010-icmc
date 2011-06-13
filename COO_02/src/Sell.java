/**
 * Classe que implementa a transação de venda.
 * @author Kiosia
 * @since 30/05/2011
 */

public class Sell extends Transaction {

	public Sell(User user, Item item) {
		super(user, item);
	}

	public Sell(User user, Item item, int qtt) {
		super(user, item, qtt);
	}

	@Override
	public void execute(User user, Item item) {
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt-this.qtt);
	}
}