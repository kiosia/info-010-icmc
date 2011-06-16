import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

/**
 * Classe abstrata que define um item.
 * Toda classe de item a ser reconhecida pelo sistema deve herdar a partir desta classe. 
 * @author Al�xis Kiosia (7152097)
 * @since 22/05/2011
 */
public abstract class Item {

	/**
	 * Define o c�digo do item.
	 */
	private String code;

	/**
	 * Define o nome do item.
	 */
	private String name;

	/**
	 * Define a unidade de medida com a qual o item � estocado.
	 */
	private String unit;

	/**
	 * Define o pre�o do item, em reais/unidade de medida.
	 */
	private double price;

	/**
	 * Define a quantidade do item em estoque.
	 */
	private int qtt;

	/**
	 * Vetor que cont�m os itens.
	 */
	public static Vector<Item> vItens = new Vector<Item>();

	/**
	 * Vetor com as classes de itens suportadas pelo sistema. 
	 */
	public static Vector<String> vItemClass = new Vector<String>();
	
	/**
	 * Construtor vazio.
	 */
	public Item(){}
	
	/**
	 * Construtor que recebe por par�metros todos os campos. 
	 * @param code Define o c�digo do item.
	 * @param name Define o nome do item.
	 * @param unit Define a unidade de medida do item.
	 * @param price Define o pre�o do item.
	 * @param qtt Define a quantiade do item em estoque.
	 */
	public Item(String code, String name, String unit, double price, int qtt) {
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.qtt = qtt;
	}

	/**
	 * Getter para o nome do item.
	 * @return Objeto do tipo String que cont�m o nome do item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter para o nome do item.
	 * @param name Objeto do tipo String que cont�m o novo nome do item.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter para o c�digo do item.
	 * @return Objeto do tipo String que cont�m o c�digo do item.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter para o c�digo do item.
	 * @param code Objeto do tipo String que cont�m o novo c�digo do item.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Getter para a unidade de medida do item.
	 * @return Objeto do tipo String que cont�m a unidade de medida do item.
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Setter para a unidade de medida do item.
	 * @param unit Objeto do tipo String que cont�m a nova unidade de medida do item.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Setter para o pre�o do item.
	 * @param price Vari�vel do tipo double que cont�m o novo pre�o do item.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Getter para o pre�o do item.
	 * @return Vari�vel do tipo double que cont�m o pre�o do item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter para a quantidade do item em estoque.
	 * @param qtt Vari�vel do tipo int que cont�m a nova quantiade do item.
	 */
	public void setQtt(int qtt) {
		this.qtt = qtt;
	}

	/**
	 * Getter para a quantidade do item em estoque.
	 * @return Vari�vel do tipo int que cont�m a quantiade do item.
	 */
	public int getQtt() {
		return qtt;
	}

	// TODO COMENTAR E ARRUMAR!
 	public static boolean loadFile(boolean newSystem) {
 		try
		{
			File aux = new File("itm.txt"); 
			if((aux.exists())&&(!newSystem))
			{
				String buffer;
				Scanner inFile = new Scanner(aux);
				Item item;
				String itemType;
				while(inFile.hasNext())
				{
					itemType = inFile.nextLine();
					Class<?> c = Class.forName(itemType);
					item = (Item) c.newInstance();
					item.setCode(inFile.nextLine());
					item.setName(inFile.nextLine());
					item.setUnit(inFile.nextLine());
					buffer = inFile.nextLine();
					item.setPrice(Double.parseDouble(buffer));
					buffer = inFile.nextLine();
					item.setQtt(Integer.parseInt(buffer));
					if(vItemClass.contains(itemType))
					{
						Item.vItens.add(item);
					}
					else
						System.out.println("Arquivo contem item do tipo invalido. Tipo: "+itemType);
				}
				inFile.close();
			}
			else
			{
					aux.createNewFile();
					System.out.println("Arquivo de itens criado!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Erro na leitura dos arquivos de itens!");
		}
		return newSystem;
 	}	
	
 	// TODO COMENTAR E ARRUMAR!
 	public static void saveFile() {
		try
		{
			PrintWriter out  = new PrintWriter(new FileWriter("itm.txt"));
			for(Item item: Item.vItens)
			{
				String itemType = item.getClass().getName();
				out.println(itemType);
				out.println(item.getCode());
				out.println(item.getName());
				out.println(item.getUnit());
				out.println(item.getPrice());
				out.println(item.getQtt());
			}
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Erro ao salvar arquivo de itens!");
		}
 	}
 	
	/**
	 * M�todo para imprimir os dados do item em quest�o na tela.
	 */
	public void printItem() {   
		DecimalFormat fmt = new DecimalFormat("0.00");    //limita o n�mero de casas decimais     
		String string = fmt.format(this.price);  
		String[] part = string.split("[,]");  
		String price = part[0]+"."+part[1];  
		System.out.println("Codigo: "+this.code+" Nome: "+this.name+" Preco: "+price+" R$/"+this.unit+" Quantidade em estoque: "+this.qtt);
	}
	
	/**
	 * M�todo que procura por um item no vetor de itens.
	 * @param itemID O c�digo do item que se quer procurar.
	 * @return Objeto do tipo Item que cont�m o item encontrado ou null se n�o encontrar nenhum.
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
	
	/**
	 * M�todo que imprime todos os itens cadastrados no sistema.
	 */
	public static void printAll() {
		for(Item i: vItens)
			i.printItem();
		System.out.println("Existe(m) "+vItens.size()+" transacao(oes)!");
	}

	/**
	 * M�todo que verificar se o item em quest�o j� existe no vetor de itens. Se n�o existir, adiciona.
	 */
	public void addItem() {
		if(Item.findItem(this.code)!=null)
		{
			vItens.add(this);
		}
		else
		{
			System.out.println("Item com este codigo ja cadastrado!");
		}
	}

	/**
	 * M�todo que remove o item em quest�o.
	 * @param itemCode Objeto do tipo String que representa o c�digo do item que se quer remover.
	 */
	public static void delItem(String itemCode) {
		Item i = Item.findItem(itemCode);
		if(i!=null)
			vItens.remove(i);
	}

}