import java.util.*;
import java.io.*;

/**
 * Classe que define o sistema.
 * @author Aléxis Kiosia (7152097)
 * @since 21/05/2011
 */
public class Sistema {
	/**
	 * Método que carrega e/ou cria os arquivos relacionados ao sistema.
	 */
	public static void loadFiles() {
		Scanner inFile;
		File aux;
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
			aux = new File("itemList.txt"); 
			if (aux.exists())
			{
				inFile = new Scanner(aux);
				Item item;
				String itemType;
				while(inFile.hasNext())
				{
					itemType = inFile.nextLine();
					c = Class.forName(itemType);
					item = (Item) c.newInstance();
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
			System.err.println("Erro na leitura dos arquivos de itens!");
		}
		try
		{	
			// arquivo de transações
			aux = new File("tranList.txt"); 
			if (aux.exists())
			{
				inFile = new Scanner(aux);
				Transaction tran;
				String tranType;
				while(inFile.hasNext())
				{
					tranType = inFile.nextLine();
					c = Class.forName(tranType);
					System.out.println(c.getName());
					tran = (Transaction) c.newInstance();
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
		// TODO Autao-generated method stub		
	}
	
	/**
	 * Variável para leitura do teclado.
	 */
	private static Scanner keyboard = new Scanner(System.in);	
	
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
		// TODO Auto-generated method stub
	}

	/**
	 * Método que cria o menu para manutenção e gerenciamento de usuários e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admUser(User logged) {
		// TODO Auto-generated method stub
		logged.edtPass();
	}

	/**
	 * Método que cria o menu para gerenciamento de produtos e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void manProd(User logged) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Método que cria o menu para manutenção e gerenciamento de produtos e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admProd(User logged) {
		// TODO Auto-generated method stub
	}
}