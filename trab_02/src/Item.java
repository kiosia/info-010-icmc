import java.util.Vector;

/**
 * Classe abstrata que os itens que irão compor o estoque.
 * Toda classe de item a ser reconhecida pelo sistema deve herdar a partir desta classe. 
 * @author Aléxis Kiosia (7152097)
 * @since 22/05/2011
 */
public abstract class Item {

	/**
	 * Define o código do item.
	 */
	private String code;
	/**
	 * Define o nome do item.
	 */
	private String name;
	/**
	 * Define a unidade de medida com a qual o item é estocado.
	 */
	private String unit;
	/**
	 * Define o preço do item, em reais/unidade de medida.
	 */
	private double price;
	/**
	 * Define a quantidade do item em estoque.
	 */
	private int qtt;

	public static Vector<Item> vItens = new Vector<Item>();
	
	public Item(){
		
	}
	
	/**
	 * Construtor que recebe como parâmetros todos os campos. 
	 * @param code Define o código do item.
	 * @param name Define o nome do item.
	 * @param unit Define a unidade de medida do item.
	 * @param price Define o preço do item.
	 * @param qtt Define a quantiade do item em estoque.
	 */
	public Item(String code, String name, String unit, double price, int qtt) {
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.qtt = qtt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setQtt(int qtt) {
		this.qtt = qtt;
	}

	public int getQtt() {
		return qtt;
	}

	public void printItem(){
		System.out.println(code+" "+name+" "+unit+" "+price+" "+qtt);
	}
	
	/**
	 * Método que procura por um item no vetor de itens.
	 * @param itemID O código do item que se quer procurar.
	 * @return Objeto do tipo Item que contém o item encontrado ou null se não encontrar nenhum.
	 */
	public static Item findItem(String itemID) {
		for(int i=0;i<vItens.size();i++)
		{
			if(vItens.get(i).code.equals(itemID))
			{
				return vItens.get(i);
			}
		}
		System.out.println("Item nao encontrado!");
		return null;
	}
}