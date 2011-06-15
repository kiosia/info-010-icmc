import java.util.*;
import java.io.*;

/**
 * Classe que define o sistema.
 * @author Al�xis Kiosia (7152097)
 * @since 21/05/2011
 */
public class Sistema {
	/**
	 * M�todo que carrega e/ou cria os arquivos relacionados ao sistema.
	 */
	public static void loadFiles() {
		Scanner inFile;
		String buffer;
		File aux;
		Class<?> c;
		try
		{
			// arquivo de usu�rios
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
			aux = new File("itm.txt"); 
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
			// arquivo de transa��es
			aux = new File("trn.txt"); 
			if (aux.exists())
			{
				inFile = new Scanner(aux);
				Transaction tran;
				String tranType;
				while(inFile.hasNext())
				{
					tranType = inFile.nextLine();
					c = Class.forName(tranType);
					tran = (Transaction) c.newInstance();
					tran.setItemCode(inFile.nextLine());
					tran.setUsername(inFile.nextLine());
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
	 * M�todo que salva os dados nos arquivos relacionados ao sistema.
	 */
	public static void saveFiles ()
	{
		PrintWriter out;;
		int i;
		try
		{
			// arquivo de usu�rios
			out  = new PrintWriter(new FileWriter("usr.txt"));
			User user;
			for(i=0;i<User.vUsers.size();i++)
			{
				user = User.vUsers.elementAt(i);
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
			Item item;
			for(i=0;i<Item.vItens.size();i++)
			{
				item = Item.vItens.elementAt(i);
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
			Transaction tran;
			// arquivo de itens
			for(i=0;i<Item.vItens.size();i++)
			{
				tran = Transaction.vTransactions.elementAt(i);
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
			System.err.println("Erro ao salvar arquivo de itens!");
		}
		
		
	}
	
	/**
	 * Vari�vel para leitura do teclado.
	 */
	private static Scanner keyboard = new Scanner(System.in);	
	
	/**
	 * M�todo que cria o menu principal e chama as fun��es necess�rias conforme a escolha do usu�rio. 
	 * @param logged O usu�rio que est� logado.
	 */
	private static void mainMenu(User logged) {
		int opt = 0;
		System.out.println("Bem vindo "+logged.getName()+"!");
		if(logged.getAdm()) // faz o menu caso o usu�rio seja um administrador
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
		else // faz o menu para usu�rios comuns
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
	 * M�todo que cria o menu para relat�rios e chama as fun��es necess�rias conforme a escolha do usu�rio.
	 * @param logged O usu�rio que est� logado.
	 */
	private static void admReport(User logged) {
		// TODO Auto-generated method stub
	}

	/**
	 * M�todo que cria o menu para manuten��o e gerenciamento de usu�rios e chama as fun��es necess�rias conforme a escolha do usu�rio.
	 * @param logged O usu�rio que est� logado.
	 */
	private static void admUser(User logged) {
		// TODO Auto-generated method stub
		logged.edtPass();
	}

	/**
	 * M�todo que cria o menu para gerenciamento de produtos e chama as fun��es necess�rias conforme a escolha do usu�rio.
	 * @param logged O usu�rio que est� logado.
	 */
	private static void manProd(User logged) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * M�todo que cria o menu para manuten��o e gerenciamento de produtos e chama as fun��es necess�rias conforme a escolha do usu�rio.
	 * @param logged O usu�rio que est� logado.
	 */
	private static void admProd(User logged) {
		// TODO Auto-generated method stub
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
		if(User.vUsers.size()==0) // se n�o existir nenhum usu�rio, cadastra o admin
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