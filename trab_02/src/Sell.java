import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe que implementa a transação de venda.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */
public class Sell extends Transaction {

	/**
	 * Construtor vazio.
	 */
	public Sell(){}
	
	/**
	 * Construtor que recebe todos os atributos por parâmetro.
	 * @param username Objeto do tipo String que representa o username do usuário que realizou a venda.
	 * @param itemCode Objeto do tipo String que representa o código do item que foi vendido.
	 * @param qtt Variável do tipo int que representa quantidade que foi vendida.
	 */
	public Sell(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}

	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		if(oldQtt<this.qtt)
		{
			System.out.println("Impossivel exectuar venda! So ha "+oldQtt+" "+item.getUnit()+" deste item em estoque.");
			return;
		}
		item.setQtt(oldQtt-this.qtt);
		vTransactions.add(this);
	}

	@Override
	public void printTransaction() {
		System.out.println(this.username+" vendeu "+this.qtt+" unidades do produto de codigo "+this.itemCode);	
	}

	@Override
	public Transaction loadTran(Scanner inFile) {
		this.action = "Venda";
		this.setUsername(inFile.nextLine());
		this.setItemCode(inFile.nextLine());
		this.setQtt(Integer.parseInt(inFile.nextLine()));
		return this;
	}

	@Override
	public void saveTran(PrintWriter out) {
		out.println("Sell");
		out.println(this.getUsername());
		out.println(this.getItemCode());
		out.println(this.getQtt());
	}


	@Override
	public void newTran(User logged) {
		Scanner keyboard = new Scanner(System.in);
		String code;
		int qtt;
		System.out.println("Digite o código do produto a ser vendido:");
		code = keyboard.nextLine();
		if(Item.findItem(code)==null)
		{
			System.out.println("Produto nao encontrado.");
			return;
		}
		System.out.println("Digite a quantidade de produtos a serem vendidos:");
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