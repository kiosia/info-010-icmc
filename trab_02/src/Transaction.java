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
	public Transaction(User user, Item item, int qtt) {
		this.user = user;
		this.item = item;
		this.qtt = qtt;
	}

	/**
	 * Getter para o item relacionado à transação.
	 * @return Objeto do tipo Item envolvido na transação. 
	 */
	public Item getItem() {
		return item;
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
		return this.user;
	}

	/**
	 * Método que imprime na tela os dados da transação em questão.
	 */
	public void printTransaction(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * Método que procura todas as transações relacionadas ao usuário recebido por parâmetro.
	 * @param user Objeto do tipo User pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
	 */	
	public static int listByUser(User user) {
		int qtt = 0;
		for(int i=0;i<vTransactions.size();i++)
		{
			if(vTransactions.get(i).user == user)
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
	 * @param item Objeto do tipo Item pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
	 */
	public static int listByItem(Item item) {
		int qtt = 0;
		for(int i=0;i<vTransactions.size();i++)
		{
			if(vTransactions.get(i).item == item)
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
	 * Método virtual para carregar o arquivo da transação específica.
	 */
	public abstract void loadFile();

}