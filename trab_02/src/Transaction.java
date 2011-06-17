import java.io.*;
import java.util.*;

/**
 * Classe abstrata que define uma transa��o.
 * Toda classe de transa��o a ser reconhecida pelo sistema deve herdar a partir desta classe.
 * @author Al�xis Kiosia (7152097)
 * @since 22/05/2011
 */
public abstract class Transaction {

	/**
	 * Define o verbo que vai aparecer nos menus.
	 */
	public String action;
	
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
	 * Vetor com as classes de transa��es suportadas pelo sistema. 
	 */
	public static Vector<String> vTranClass = new Vector<String>();
	
	/**
	 * Construtor vazio
	 */
	public Transaction() {}
	
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
	 * Getter para o username.
	 * @return Objeto do tipo String que cont�m o username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter para o username.
	 * @param username Objeto do tipo String que cont�m o novo username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter para o c�digo do item.
	 * @return Objeto do tipo String que cont�m o c�digo do item.
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Setter para o c�digo do item.
	 * @param itemCode Objeto do tipo String que cont�m o novo c�digo do item.
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * Setter para a quantidade envolvida na transa��o.
	 * @param qtt Vari�vel do tipo int que cont�m a nova quantidade.
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
	 * M�todo que carrega as transa��es do arquivo.
	 * @param newSystem Vari�vel do tipo boolean que define se o arquivo deve ser criado do zero (true caso deva).
	 */
	public static void loadFile(boolean newSystem) {
		try
		{
			File aux = new File("trn"); 
			if((aux.exists())&&(!newSystem))
			{
				Scanner inFile = new Scanner(aux);
				while(inFile.hasNext())
					vTransactions.add(((Transaction) Class.forName(inFile.nextLine()).newInstance()).loadTran(inFile));
				inFile.close();
			}
			else
			{
				aux.createNewFile();
				System.out.println("Arquivo de transacoes criado!");
			}
		}
		catch (Exception e)
		{
			System.err.println("Erro na leitura dos arquivos de transacoes!");
		}
	}

	/**
	 * M�todo que percorre o vetor de transa��es e chama a fun��o saveTran() de cada uma delas, salvando-as no arquivo.
	 */
	public static void saveFile() {
		try
		{
			PrintWriter out  = new PrintWriter(new FileWriter("trn"));
			for(Transaction tran: Transaction.vTransactions)
				tran.saveTran(out);
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Erro ao salvar arquivo de transacoes!");
		}
	}
	
	/**
	 * M�todo que procura todas as transa��es relacionadas ao usu�rio recebido por par�metro.
	 * @param username Objeto do tipo User pelo qual se quer pesquisar nas transa��es.
	 * @return Vari�vel do tipo int que indica a quantidade de transa��es encontradas. 
	 */	
	public static int listByUser(String username) {
		int qtt = 0;
		for(Transaction t: vTransactions)
			if(t.username.equals(username))
			{
				t.printTransaction();
				qtt++;
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
		for(Transaction t: vTransactions)
			if(t.itemCode.equals(itemCode))
			{
				t.printTransaction();
				qtt++;
			}
		System.out.println("Existe(m) "+qtt+" transacao(oes) para este item!");
		return qtt;
	}

	/**
	 * M�todo que imprime todas as transa��es efetuadas.
	 */
	public static void printAll() {
		for(Transaction t: vTransactions)
			t.printTransaction();
		System.out.println("Existe(m) "+vTransactions.size()+" transacao(oes)!");
	}

	/**
	 * M�todo que adiciona a transa��o ao vetor.
	 */
	public void addTran() {
		vTransactions.add(this);
	}
	
	/**
	 * M�todo virtual para executar a transa��o.
	 */
	public abstract void execute();
	
	/**
	 * M�todo virtual que imprime na tela os dados da transa��o em quest�o.
	 */
	public abstract void printTransaction();
	
	/**
	 * M�todo virtual para carregar a transa��o do arquivo.
	 * @param inFile O arquivo de onde se quer ler.
	 * @return A transa��o carregada do arquivo.
	 */
	public abstract Transaction loadTran(Scanner inFile);
	
	/**
	 * M�todo virtual para salvar a transa��o no arquivo.
	 * @param out O arquivo para onde se quer salvar.
	 */
	public abstract void saveTran(PrintWriter out);

	/**
	 * M�todo virtual para coletar dados de uma transa��o e inserir no vetor.
	 * @param logged O usu�rio que efetua a transa��o.
	 */
	public abstract void newTran(User logged);
	
}