/**
 * Classe que define uma transa��o.
 * @author Al�xis Kiosia
 * @since 22/05/2011
 */
public abstract class Transaction {
/**
 * Define o usu�rio respons�vel pela transa��o
 */
	public User user;
/**
 * Define o item envolvido na transa��o.
 */
	public Item item;
/**
 * Define a quantidade de itens envolvidos na transa��o.	
 */
	public int qtt;

	/**
	 * Construtor que recebe todos os atributos.
	 * @param user Define o usu�rio que est� logado no momento da transa��o.
	 * @param item Define o item que ser� vendido.
	 * @param qtt Define a quantidade de itens envolvidos na transa��o. 
	 */
	public Transaction(User user, Item item, int qtt) {
		this.user = user;
		this.item = item;
		this.qtt = qtt;
	}

	/**
	 * Construtor que n�o recebe qtt. Dessa forma, considera-se apenas 1 item.
	 * @param user Define o usu�rio que est� logado no momento da transa��o.
	 * @param item Define o item que ser� vendido.
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
	 * M�todo virtual para executar a transa��o quando esta n�o envolve quantidade.
	 * @param user Define o usu�rio envolvido na transa��o.
	 * @param item Define o item envolvido na transa��o.
	 */
	public abstract void execute(User user, Item item);
		
}