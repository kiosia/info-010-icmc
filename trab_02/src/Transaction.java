import java.util.Vector;

/**
 * Classe abstrata que define uma transação.
 * Toda classe de transação a ser reconhecida pelo sistema deve herdar a partir desta classe.
 * @author Aléxis Kiosia (7152097)
 * @since 22/05/2011
 */
public abstract class Transaction {
/**
 * Define o usuário responsável pela transação
 */
	public String username;
/**
 * Define o item envolvido na transação.
 */
	public String itemCode;
/**
 * Define a quantidade de itens envolvidos na transação.	
 */
	public int qtt;
/**
 * Vetor que contém as transações. 
 */
	public static Vector<Transaction> vTransactions = new Vector<Transaction>();	
	
	/**
	 * Construtor vazio
	 */
	public Transaction() {

	}
	
	/**
	 * Construtor que recebe todos os atributos.
	 * @param user Define o usuário que está logado no momento da transação.
	 * @param item Define o item que será vendido.
	 * @param qtt Define a quantidade de itens envolvidos na transação. 
	 */
	public Transaction(String user, String item, int qtt) {
		this.username = user;
		this.itemCode = item;
		this.qtt = qtt;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @param qtt the qtt to set
	 */
	public void setQtt(int qtt) {
		this.qtt = qtt;
	}

	/**
	 * Getter para o item relacionado à transação.
	 * @return Objeto do tipo Item envolvido na transação. 
	 */
	public Item getItem() {
		return Item.findItem(this.itemCode);
	}

	/**
	 * Getter para a quantidade relacionado à transação.
	 * @return Inteiro que representa a quantidade envolvida na transação.
	 */
	public int getQtt() {
		return qtt;
	}
	
	/**
	 * Getter para o usuário relacionado à transação.
	 * @return Objeto do tipo User envolvido na transação.
	 */
	public User getUser(){
		return User.findUser(this.username);
	}

	/**
	 * Método que procura todas as transações relacionadas ao usuário recebido por parâmetro.
	 * @param username Objeto do tipo User pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
	 */	
	public static int listByUser(String username) {
		int qtt = 0;
		for(int i=0;i<vTransactions.size();i++)
		{
			if(vTransactions.get(i).username == username)
			{
				vTransactions.get(i).printTransaction();
				qtt++;
			}
		}
		System.out.println("Existe(m) "+qtt+" transacao(oes) para este usuario!");
		return qtt;
	}
	
	/**
	 * Método que procura todas as transações relacionadas ao item recebido por parâmetro.
	 * @param itemCode Objeto do tipo Item pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
	 */
	public static int listByItem(String itemCode) {
		int qtt = 0;
		for(int i=0;i<vTransactions.size();i++)
		{
			if(vTransactions.get(i).itemCode == itemCode)
			{
				vTransactions.get(i).printTransaction();
				qtt++;
			}
		}
		System.out.println("Existe(m) "+qtt+" transacao(oes) para este item!");
		return qtt;
	}
	
	/**
	 * Método virtual para executar a transação.
	 */
	public abstract void execute();
	
	/**
	 * Método virtual que imprime na tela os dados da transação em questão.
	 */
	public abstract void printTransaction();
	
}