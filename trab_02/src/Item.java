import java.io.*;
import java.util.*;

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

	/**
	 * M�todo que carrega os itens do arquivo.
	 * @param newSystem Vari�vel do tipo boolean que define se o arquivo deve ser criado do zero (true caso deva).
	 */
 	public static void loadFile(boolean newSystem) {
 		try
		{
			File aux = new File("itm"); 
			if((aux.exists())&&(!newSystem))
			{
				Scanner inFile = new Scanner(aux);
				while(inFile.hasNext())
					Item.vItens.add(((Item) Class.forName(inFile.nextLine()).newInstance()).loadItem(inFile));
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
			System.err.println("Erro na leitura dos arquivos de itens!");
		}
 	}	
	
	/**
	 * M�todo que percorre o vetor de itens e chama a fun��o saveItem() de cada um deles, salvando-os no arquivo.
	 */
 	public static void saveFile() {
		try
		{
			PrintWriter out  = new PrintWriter(new FileWriter("itm"));
			for(Item item: Item.vItens)
				item.saveItem(out);
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Erro ao salvar arquivo de itens!");
		}
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
		return null;
	}
	
	/**
	 * M�todo que imprime todos os itens cadastrados no sistema.
	 */
	public static void printAll() {
		for(Item i: vItens)
			i.printItem();
		System.out.println("Existe(m) "+vItens.size()+" item(ns)!");
	}

	/**
	 * M�todo que verifica se o item em quest�o j� existe no vetor de itens. Se n�o existir, adiciona.
	 */
	public void addItem() {
		if(Item.findItem(this.code)==null)
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
		else
			System.out.println("Item nao encontrado.");
	}

	/**
	 * M�todo virtual para carregar o item do arquivo.
	 * @param inFile O arquivo de onde se quer ler.
	 * @return O item carregado do arquivo.
	 */
 	public abstract Item loadItem(Scanner inFile);

	/**
	 * M�todo virtual para salvar o item no arquivo.
	 * @param out O arquivo para onde se quer salvar.
	 */
	public abstract void saveItem(PrintWriter out);	
 
	/**
	 * M�todo virtual para imprimir os dados do item em quest�o na tela.
	 */
	public abstract void printItem();

	/**
	 * M�todo virtual para coletar dados de um item e inserir no vetor.
	 */
	public abstract void newItem();
}