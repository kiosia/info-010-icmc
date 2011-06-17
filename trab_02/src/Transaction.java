import java.io.*;
import java.util.*;

/**
 * Classe abstrata que define uma transação.
 * Toda classe de transação a ser reconhecida pelo sistema deve herdar a partir desta classe.
 * @author Aléxis Kiosia (7152097)
 * @since 22/05/2011
 */
public abstract class Transaction {

	/**
	 * Define o verbo que vai aparecer nos menus.
	 */
	public String action;
	
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
	 * Vetor com as classes de transações suportadas pelo sistema. 
	 */
	public static Vector<String> vTranClass = new Vector<String>();
	
	/**
	 * Construtor vazio
	 */
	public Transaction() {}
	
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
	 * Getter para o username.
	 * @return Objeto do tipo String que contém o username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter para o username.
	 * @param username Objeto do tipo String que contém o novo username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter para o código do item.
	 * @return Objeto do tipo String que contém o código do item.
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Setter para o código do item.
	 * @param itemCode Objeto do tipo String que contém o novo código do item.
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * Setter para a quantidade envolvida na transação.
	 * @param qtt Variável do tipo int que contém a nova quantidade.
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
	 * Método que carrega as transações do arquivo.
	 * @param newSystem Variável do tipo boolean que define se o arquivo deve ser criado do zero (true caso deva).
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
	 * Método que percorre o vetor de transações e chama a função saveTran() de cada uma delas, salvando-as no arquivo.
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
	 * Método que procura todas as transações relacionadas ao usuário recebido por parâmetro.
	 * @param username Objeto do tipo User pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
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
	 * Método que procura todas as transações relacionadas ao item recebido por parâmetro.
	 * @param itemCode Objeto do tipo Item pelo qual se quer pesquisar nas transações.
	 * @return Variável do tipo int que indica a quantidade de transações encontradas. 
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
	 * Método que imprime todas as transações efetuadas.
	 */
	public static void printAll() {
		for(Transaction t: vTransactions)
			t.printTransaction();
		System.out.println("Existe(m) "+vTransactions.size()+" transacao(oes)!");
	}

	/**
	 * Método que adiciona a transação ao vetor.
	 */
	public void addTran() {
		vTransactions.add(this);
	}
	
	/**
	 * Método virtual para executar a transação.
	 */
	public abstract void execute();
	
	/**
	 * Método virtual que imprime na tela os dados da transação em questão.
	 */
	public abstract void printTransaction();
	
	/**
	 * Método virtual para carregar a transação do arquivo.
	 * @param inFile O arquivo de onde se quer ler.
	 * @return A transação carregada do arquivo.
	 */
	public abstract Transaction loadTran(Scanner inFile);
	
	/**
	 * Método virtual para salvar a transação no arquivo.
	 * @param out O arquivo para onde se quer salvar.
	 */
	public abstract void saveTran(PrintWriter out);

	/**
	 * Método virtual para coletar dados de uma transação e inserir no vetor.
	 * @param logged O usuário que efetua a transação.
	 */
	public abstract void newTran(User logged);
	
}