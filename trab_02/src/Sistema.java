import java.util.*;
import java.io.*;

/**
 * Classe abstrata que define o sistema. Não podem haver objetos instanciados desta classe.
 * @author Aléxis Kiosia (7152097)
 * @since 21/05/2011
 */
public abstract class Sistema {
	/**
	 * Método que carrega e/ou cria os arquivos relacionados ao sistema.
	 */
	public static void loadFiles() {
		Scanner inFile;
		String buffer;
		File aux;
		boolean newSystem = false;
		Class<?> c;
		try
		{
			// arquivo de usuários
			aux = new File("usr.txt");
			if (aux.exists())
			{
				inFile = new Scanner(aux);
				while(inFile.hasNext())
				{
					String name = inFile.nextLine();
					String username = inFile.nextLine();
					String password = inFile.nextLine();
					boolean adm = inFile.nextLine().equals("1");
					User user = new User(name, username, password, adm, true);
					User.vUsers.add(user);
				}
				inFile.close();
			}
			else
			{
				aux.createNewFile();
				newSystem = true; // Se não houver arquivo de usuários, o sistema está comprometido. Refaz-se TODOS os arquivos.
				System.out.println("Arquivo de usuarios criado!");
			}
		}
		catch (Exception e)
		{
			System.err.println("Erro na leitura do arquivo de usuarios!");
		}
		try
		{
			// arquivo de itens
			aux = new File("itm.txt"); 
			if((aux.exists())&&(!newSystem))
			{
				inFile = new Scanner(aux);
				Item item;
				String itemType;
				while(inFile.hasNext())
				{
					itemType = inFile.nextLine();
					c = Class.forName(itemType);
					item = (Item) c.newInstance();
					item.setCode(inFile.nextLine());
					item.setName(inFile.nextLine());
					item.setUnit(inFile.nextLine());
					buffer = inFile.nextLine();
					item.setPrice(Double.parseDouble(buffer));
					buffer = inFile.nextLine();
					item.setQtt(Integer.parseInt(buffer));
					Item.vItens.add(item);
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
		try
		{
			// arquivo de transações
			aux = new File("trn.txt"); 
			if((aux.exists())&&(!newSystem))
			{
				inFile = new Scanner(aux);
				Transaction tran;
				String tranType;
				while(inFile.hasNext())
				{
					tranType = inFile.nextLine();
					c = Class.forName(tranType);
					tran = (Transaction) c.newInstance();
					tran.setUsername(inFile.nextLine());
					tran.setItemCode(inFile.nextLine());
					buffer = inFile.nextLine();
					tran.setQtt(Integer.parseInt(buffer));
					Transaction.vTransactions.add(tran);
				}
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
	 * Método que salva os dados nos arquivos relacionados ao sistema.
	 */
	public static void saveFiles ()
	{
		PrintWriter out;;
		try
		{
			// arquivo de usuários
			out  = new PrintWriter(new FileWriter("usr.txt"));
			for(User user: User.vUsers)
			{
				out.println(user.getName());
				out.println(user.getUsername());
				out.println(user.getPassword());
				if(user.getAdm())
					out.println("1");
				else
					out.println("0");
			}
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Erro ao salvar arquivo de usuarios!");
		}
		try
		{
			// arquivo de itens
			out  = new PrintWriter(new FileWriter("itm.txt"));
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
		try
		{
			out  = new PrintWriter(new FileWriter("trn.txt"));
			// arquivo de itens
			for(Transaction tran: Transaction.vTransactions)
			{
				String tranType = tran.getClass().getName();
				out.println(tranType);
				out.println(tran.getUsername());
				out.println(tran.getItemCode());
				out.println(tran.getQtt());
			}
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Erro ao salvar arquivo de transacoes!");
		}
	}
	
	/**
	 * Variável para leitura do teclado.
	 */
	private static Scanner keyboard = new Scanner(System.in);	
	
	/**
	 * Método que cria o menu principal e chama as funções necessárias conforme a escolha do usuário. 
	 * @param logged O usuário que está logado.
	 */
	private static void mainMenu(User logged) {
		int opt = 0;
		System.out.println("Bem vindo "+logged.getName()+"!");
		if(logged.getAdm()) // faz o menu caso o usuário seja um administrador
		{
			while(opt!=4)
			{
				System.out.println("1 - Gerenciar produtos");
				System.out.println("2 - Gerenciar usuarios");
				System.out.println("3 - Gerar relatorios");
				System.out.println("4 - Logout");
				System.out.println("5 - Sair");
				opt = keyboard.nextInt();
				switch(opt)
				{
					case 1 :
						admProd(logged);
						break;
					case 2 :
						admUser(logged);
						break;
					case 3 :
						admReport(logged);
						break;
					case 4 :
						System.out.println("Usuario "+logged.getUsername()+" deslogando...");
						break;
					case 5 :
						System.out.println("Obrigado por utilizar o STOC!");
						saveFiles();
						System.exit(0);
					default :
						System.out.println("Opcao invalida!");
				}
			}
		}
		else // faz o menu para usuários comuns
		{
			while(opt!=3)
			{
				System.out.println("1 - Transacoes");
				System.out.println("2 - Trocar senha");
				System.out.println("3 - Logout");
				System.out.println("4 - Sair");
				opt = keyboard.nextInt();
				switch(opt)
				{
					case 1 :
						manProd(logged);
						break;
					case 2 :
						logged.edtPass();
						break;
					case 3 :
						System.out.println("Usuario "+logged.getUsername()+" deslogando...");
						break;
					case 4 :
						System.out.println("Obrigado por utilizar o STOC!");
						saveFiles();
						System.exit(0);
					default :
						System.out.println("Opcao invalida!");
				}
			}			
		}
	}

	/**
	 * Método que cria o menu para relatórios e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admReport(User logged) {
		int opt = 0;
		while(opt!=5)
		{
			System.out.println("1 - Verificar produtos cadastrados");
			System.out.println("2 - Verificar transacoes efetuadas");
			System.out.println("3 - Verificar transacoes efetuadas por usuario");
			System.out.println("4 - Verificar transacoes efetuadas por item");
			System.out.println("5 - Voltar");
			opt = keyboard.nextInt();
			switch(opt)
			{
				case 1 :
					Item.printAll();
					break;
				case 2 :
					Transaction.printAll();
					break;
				case 3 :
					System.out.println("Digite o username do usuario:");
					String user = keyboard.nextLine();
					Transaction.listByUser(user);
					break;
				case 4 :
					System.out.println("Digite o codigo do produto:");
					String item = keyboard.nextLine();
					Transaction.listByItem(item);
					break;
				case 5 :
					break;
				default :
					System.out.println("Opcao invalida!");
			}
		}
		
	}

	/**
	 * Método que cria o menu para manutenção e gerenciamento de usuários e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admUser(User logged) {
		int opt = 0;
		while(opt!=6)
		{
			System.out.println("1 - Alterar a senha");
			System.out.println("2 - Cadastrar novo usuario");
			System.out.println("3 - Excluir um usuario");
			System.out.println("4 - Editar administradores");
			System.out.println("5 - Exibir usuarios");
			System.out.println("6 - Voltar");
			opt = keyboard.nextInt();
			switch(opt)
			{
				case 1 :
					logged.edtPass();
					break;
				case 2 :
					User.addUser();
					break;
				case 3 :
					User.delUser(logged);
					break;
				case 4 :
					User.edtAdm(logged);
					break;
				case 5 :
					User.printAll();
					break;
				case 6 :
					break;
				default :
					System.out.println("Opcao invalida!");
			}
		}
	}

	/**
	 * Método que cria o menu para gerenciamento de produtos e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void manProd(User logged) {
		int opt = 0;
		String item;
		Transaction t;
		int qtt;
		do
		{
			keyboard.nextLine();
			System.out.println("Digite o codigo do produto:");
			item = keyboard.nextLine();
			System.out.println("Digite a quantidade envolvida na transacao:");
			qtt = keyboard.nextInt();
		} while(Item.findItem(item)==null);
		
		while(opt!=3)
		{
			System.out.println("Voce quer...");
			System.out.println("1 - Vender o produto?");
			System.out.println("2 - Comprar o produto?");
			System.out.println("3 - Voltar?");
			opt = keyboard.nextInt();
			switch(opt)
			{
				case 1 :
					t = new Sell(logged.getUsername(), item, qtt);
					t.execute();
					return;
				case 2 :
					t = new Buy(logged.getUsername(), item, qtt);
					t.execute();
					return;
				case 3 :
					break;
				default :
					System.out.println("Opcao invalida!");
			}
		}
		
	}
	
	/**
	 * Método que cria o menu para manutenção e gerenciamento de produtos e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admProd(User logged) {
		// TODO admProd Método que cria o menu para manutenção e gerenciamento de produtos e chama as funções necessárias conforme a escolha do usuário.
	}

	/**
	 * Inicia e roda o sistema.
	 * @param args Vetor de argumentos passados via console
	 */
	public static void main(String[] args) {
		loadFiles();
		String username, password;
		User aux; 
		System.out.println("\nSistema STOC - Stoc: Transacoes de Objetos Colossais\n");
		if(User.vUsers.size()==0) // se não existir nenhum usuário, cadastra o admin
		{ 
			aux = new User("Administrador", "admin", "admin", true, false);
			System.out.println("Primeiro acesso!\nAdministrador criado!\nUsername: admin Password: admin");
			System.out.println("Troque sua senha assim que possivel!\n");
		}
		while(true)
		{
			System.out.println("Digite seu nome de usuario: (digite SAIR para sair)");
			username = keyboard.next();
			if((username.equals("SAIR"))||(username.equals("sair")))
			{
				System.out.println("Obrigado por utilizar o STOC!");
				saveFiles();
				return;
			}
			System.out.println("Digite sua senha:");
			password = keyboard.next();
			aux = User.log(username, password); 
			if(aux!=null)
			{
				mainMenu(aux);
			}
			saveFiles();
		}
	}
	
}