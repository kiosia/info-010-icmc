import java.util.Vector;

/**
 * Classe abstrata que define uma transa��o.
 * Toda classe de transa��o a ser reconhecida pelo sistema deve herdar a partir desta classe.
 * @author Al�xis Kiosia (7152097)
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
 * Vetor que cont�m as transa��es. 
 */
	public static Vector<Transaction> vTransactions = new Vector<Transaction>();	
	
	/**
	 * Construtor vazio
	 */
	public Transaction() {

	}
	
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
	 * Getter para o item relacionado � transa��o.
	 * @return Objeto do tipo Item envolvido na transa��o. 
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Getter para a quantidade relacionado � transa��o.
	 * @return Inteiro que representa a quantidade envolvida na transa��o.
	 */
	public int getQtt() {
		return qtt;
	}
	
	/**
	 * Getter para o usu�rio relacionado � transa��o.
	 * @return Objeto do tipo User envolvido na transa��o.
	 */
	public User getUser(){
		return this.user;
	}

	/**
	 * M�todo que imprime na tela os dados da transa��o em quest�o.
	 */
	public void printTransaction(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * M�todo que procura todas as transa��es relacionadas ao usu�rio recebido por par�metro.
	 * @param user Objeto do tipo User pelo qual se quer pesquisar nas transa��es.
	 * @return Vari�vel do tipo int que indica a quantidade de transa��es encontradas. 
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
	 * M�todo que procura todas as transa��es relacionadas ao item recebido por par�metro.
	 * @param item Objeto do tipo Item pelo qual se quer pesquisar nas transa��es.
	 * @return Vari�vel do tipo int que indica a quantidade de transa��es encontradas. 
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
	 * M�todo virtual para executar a transa��o.
	 */
	public abstract void execute();
	
	/**
	 * M�todo virtual para carregar o arquivo da transa��o espec�fica.
	 */
	public abstract void loadFile();

}