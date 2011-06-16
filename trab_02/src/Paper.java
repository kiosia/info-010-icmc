import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Classe que implementa tipos de item derivados de Papel.
 * @author Aléxis Kiosia (7152097)
 * @since 15/06/2011
 */
public class Paper extends Item {

	/**
	 * Construtor vazio.
	 */
	public Paper(){}
	
	/**
	 * Construtor que recebe todos os atributos por parâmetro.
	 * @param code Objeto do tipo String que representa o código do item.
	 * @param name Objeto do tipo String que representa o nome do item.
	 * @param unit Objeto do tipo String que representa a unidade de medida do item.
	 * @param price Variável do tipo double que representa o preço do item.
	 * @param qtt Variável do tipo int que representa a quantidade do item em estoque.
	 */
	public Paper(String code, String name, String unit, double price, int qtt) {
		super(code, name, unit, price, qtt);
	}

	@Override
	public Item loadItem(Scanner inFile) {
		this.setCode(inFile.nextLine());
		this.setName(inFile.nextLine());
		this.setUnit(inFile.nextLine());
		this.setPrice(Double.parseDouble(inFile.nextLine()));
		this.setQtt(Integer.parseInt(inFile.nextLine()));
		return this;
	}

	@Override
	public void saveItem(PrintWriter out) {
		out.println("Paper");
		out.println(this.getCode());
		out.println(this.getName());
		out.println(this.getUnit());
		out.println(this.getPrice());
		out.println(this.getQtt());
	}

	@Override
	public void printItem()
	{   
		DecimalFormat fmt = new DecimalFormat("0.00");    //limita o número de casas decimais     
		String string = fmt.format(this.getPrice());  
		String[] part = string.split("[,]");  
		String price = part[0]+"."+part[1];  
		System.out.println("Codigo: "+this.getCode()+" Nome: "+this.getName()+" Preco: "+price+" R$/"+this.getUnit()+" Quantidade em estoque: "+this.getQtt());
	}

	
	@Override
	public void newItem() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Digite o codigo do item:");
		this.setCode(keyboard .nextLine());
		System.out.println("Digite o nome do item:");
		this.setName(keyboard.nextLine());
		System.out.println("Digite a unidade de medida do item:");
		this.setUnit(keyboard.nextLine());
		System.out.println("Digite o preco do item:");
		this.setPrice(Double.parseDouble(keyboard.nextLine()));
		this.setQtt(0); // item adicionado não foi comprado
		this.addItem();
	}
}