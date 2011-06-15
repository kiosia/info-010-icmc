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
	public String username;
/**
 * Define o item envolvido na transa��o.
 */
	public String itemCode;
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
	 * Getter para o item relacionado � transa��o.
	 * @return Objeto do tipo Item envolvido na transa��o. 
	 */
	public Item getItem() {
		return Item.findItem(this.itemCode);
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
		return User.findUser(this.username);
	}

	/**
	 * M�todo que procura todas as transa��es relacionadas ao usu�rio recebido por par�metro.
	 * @param username Objeto do tipo User pelo qual se quer pesquisar nas transa��es.
	 * @return Vari�vel do tipo int que indica a quantidade de transa��es encontradas. 
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
	 * M�todo que procura todas as transa��es relacionadas ao item recebido por par�metro.
	 * @param itemCode Objeto do tipo Item pelo qual se quer pesquisar nas transa��es.
	 * @return Vari�vel do tipo int que indica a quantidade de transa��es encontradas. 
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
	 * M�todo virtual para executar a transa��o.
	 */
	public abstract void execute();
	
	/**
	 * M�todo virtual que imprime na tela os dados da transa��o em quest�o.
	 */
	public abstract void printTransaction();
	
}