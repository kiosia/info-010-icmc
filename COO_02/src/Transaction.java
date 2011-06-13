/**
 * Classe que define uma transação.
 * @author Aléxis Kiosia
 * @since 22/05/2011
 */
public abstract class Transaction {
/**
 * Define o usuário responsável pela transação
 */
	public User user;
/**
 * Define o item envolvido na transação.
 */
	public Item item;
/**
 * Define a quantidade de itens envolvidos na transação.	
 */
	public int qtt;

	/**
	 * Construtor que recebe todos os atributos.
	 * @param user Define o usuário que está logado no momento da transação.
	 * @param item Define o item que será vendido.
	 * @param qtt Define a quantidade de itens envolvidos na transação. 
	 */
	public Transaction(User user, Item item, int qtt) {
		this.user = user;
		this.item = item;
		this.qtt = qtt;
	}

	/**
	 * Construtor que não recebe qtt. Dessa forma, considera-se apenas 1 item.
	 * @param user Define o usuário que está logado no momento da transação.
	 * @param item Define o item que será vendido.
	 */
	public Transaction(User user, Item item) {
		this.user = user;
		this.item = item;
		this.qtt = 1;
	}

	public Item getItem() {
		return item;
	}

	public int getQtt() {
		return qtt;
	}
	
	/**
	 * Método virtual para executar a transação quando esta não envolve quantidade.
	 * @param user Define o usuário envolvido na transação.
	 * @param item Define o item envolvido na transação.
	 */
	public abstract void execute(User user, Item item);
		
}