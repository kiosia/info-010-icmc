/**
 * Classe que implementa a transação de venda.
 * @author Aléxis Kiosia (7152097)
 * @since 30/05/2011
 */

public class Sell extends Transaction {

	public Sell(){

	}
	
	public Sell(String username, String itemCode, int qtt) {
		super(username, itemCode, qtt);
	}

	@Override
	public void execute() {
		Item item = Item.findItem(this.itemCode);
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt-this.qtt);
		vTransactions.add(this);
	}

//	@Override
//	public void loadFile() {
//		Scanner inFile;
//		File aux;
//		try
//		{
//			// arquivo de vendas
//			aux = new File("sell.txt");
//			if (aux.exists())
//			{
//				inFile = new Scanner(aux);
//				String userName, itemID;
//				while(inFile.hasNext())
//				{
//					userName = inFile.nextLine();
//					itemID = inFile.nextLine();
//					this.qtt = inFile.nextInt();
//					this.username = userName;
//					this.itemCode = itemID;
//					vTransactions.add(this);
//				}
//				inFile.close();
//			}
//			else
//			{
//					aux.createNewFile();
//					System.out.println("Arquivo de vendas criado!");
//			}
//		}
//		catch (Exception e)
//		{
//			System.err.println("Erro na leitura do arquivo de vendas!");
//		}
//	}

	@Override
	public void printTransaction() {
		// TODO Auto-generated method stub
		System.out.println(this.username+" vendeu "+this.qtt+" unidades do produto de codigo "+this.itemCode);
		
	}
}