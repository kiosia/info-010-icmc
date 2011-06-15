/**
 * Classe que implementa a transação de compra.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */

public class Buy extends Transaction {

	public Buy(){
		loadFile();
	}
	
	public Buy(User user, Item item, int qtt) {
		super(user, item, qtt);
	}
	
	@Override
	public void execute() {
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt+this.qtt);
	}

	@Override
	public void loadFile() {
		// TODO Auto-generated method stub
		System.out.println("Carregando compras!");
	}

}
