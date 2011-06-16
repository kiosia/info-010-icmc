import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe que implementa a transação de compra.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Buy extends Transaction {

	/**
	 * Construtor vazio.
	 */
	public Buy(){}
	
	/**
	 * Construtor que recebe todos os atributos por parâmetro.
	 * @param username Objeto do tipo String que representa o username do usuário que realizou a compra.
	 * @param itemCode Objeto do tipo String que representa o código do item que foi comprado.
	 * @param qtt Variável do tipo int que representa quantidade que foi comprada.
	 */
	public Buy(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}
	
	/**
	 * Método que executa a compra.
	 */
	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt+this.qtt);
		vTransactions.add(this);
	}

	/**
	 * Método que imprime a transação de compra.
	 */
	@Override
	public void printTransaction() {
		System.out.println(this.username+" comprou "+this.qtt+" unidades do produto de codigo "+this.itemCode);
		
	}

	@Override
	public Transaction loadTran(Scanner inFile) {
		this.action = "Compra";
		this.setUsername(inFile.nextLine());
		this.setItemCode(inFile.nextLine());
		this.setQtt(Integer.parseInt(inFile.nextLine()));
		return this;
	}

	@Override
	public void saveTran(PrintWriter out) {
		out.println("Buy");
		out.println(this.getUsername());
		out.println(this.getItemCode());
		out.println(this.getQtt());
	}


	@Override
	public void newTran(User logged) {
		Scanner keyboard = new Scanner(System.in);
		String code;
		int qtt;
		System.out.println("Digite o código do produto a ser comprado:");
		code = keyboard.nextLine();
		if(Item.findItem(code)==null)
		{
			System.out.println("Produto nao encontrado.");
			return;
		}
		System.out.println("Digite a quantidade de produtos a serem comprados:");
		qtt = Integer.parseInt(keyboard.nextLine());
		if(qtt<=0)
		{
			System.out.println("Quantidade deve ser maior que 0.");
			return;
		}
		this.username = logged.getUsername();
		this.itemCode = code;
		this.qtt = qtt;
		this.execute();
	}	

}