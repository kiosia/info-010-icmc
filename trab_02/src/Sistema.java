import java.util.*;
import java.io.*;

/**
 * Classe abstrata que define o sistema. Não podem haver objetos instanciados desta classe.
 * @author Aléxis Kiosia (7152097)
 * @since 21/05/2011
 */
public abstract class Sistema {

	/**
	 * Construtor vazio.
	 */
	public Sistema(){}

	/**
	 * Método que carrega e/ou cria os arquivos relacionados ao sistema.
	 */
	public static void loadFiles() {
		loadConfig();
		boolean newSystem = User.loadFile();
		newSystem = Item.loadFile(newSystem);
		Transaction.loadFile(newSystem);
	}
	 
	/**
	 * Método que carrega o arquivo de configurações. Se este arquivo estiver corrompido, o sistema não é iniciado.
	 */
 	public static void loadConfig() {
		try
		{
			File aux = new File("cfg.txt");
			if (aux.exists())
			{
				String buffer;
				Scanner inFile = new Scanner(aux);
				while(inFile.hasNext())
				{
					buffer = inFile.nextLine();
					if(buffer.equals("Item"))
					{
						buffer = inFile.nextLine();
						while(!buffer.equals("Transaction"))
						{
							Item.vItemClass.add(buffer);
							buffer = inFile.nextLine();
						}
						while(inFile.hasNext())
						{
							Transaction.vTranClass.add(buffer);
							buffer = inFile.nextLine();
						}
					}
					else
					{
						System.out.println("Sistema corrompido! Entre em contato com o desenvolvedor.");
						System.exit(0);
					}
				}
				inFile.close();
			}
			else
			{
				// Se não houver arquivo de configurações, o sistema está corrompido e não poderá ser utilizado.
				System.out.println("Sistema corrompido! Entre em contato com o desenvolvedor.");
				System.exit(0);
			}
		}
		catch (Exception e)
		{
			System.out.println("Sistema corrompido! Entre em contato com o desenvolvedor.");
			System.exit(0);
		}
		if((Transaction.vTranClass.size()==0)||(Item.vItemClass.size()==0))
		{
			System.out.println("Sistema corrompido! Entre em contato com o desenvolvedor.");
			System.exit(0);
		}
 	}

	/**
	 * Método que salva os dados nos arquivos relacionados ao sistema.
	 */
	public static void saveFiles ()
	{
		User.saveFile();
		Item.saveFile();
		Transaction.saveFile();
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
				System.out.println("1 - Transacoes");
				System.out.println("2 - Gerenciar produtos");
				System.out.println("3 - Gerenciar usuarios");
				System.out.println("4 - Gerar relatorios");
				System.out.println("5 - Logout");
				System.out.println("6 - Sair");
				opt = keyboard.nextInt();
				switch(opt)
				{
					case 1:
						manProd(logged);
						break;
					case 2 :
						admProd(logged);
						break;
					case 3 :
						admUser(logged);
						break;
					case 4 :
						admReport(logged);
						break;
					case 5 :
						System.out.println("Usuario "+logged.getUsername()+" deslogando...");
						break;
					case 6 :
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
			System.out.println("1 - Verificar transacoes efetuadas");
			System.out.println("2 - Verificar transacoes efetuadas por usuario");
			System.out.println("3 - Verificar transacoes efetuadas por item");
			System.out.println("4 - Voltar");
			opt = keyboard.nextInt();
			switch(opt)
			{
				case 1 :
					Transaction.printAll();
					break;
				case 2 :
					System.out.println("Digite o username do usuario:");
					String user = keyboard.nextLine();
					Transaction.listByUser(user);
					break;
				case 3 :
					System.out.println("Digite o codigo do produto:");
					String item = keyboard.nextLine();
					Transaction.listByItem(item);
					break;
				case 4 :
					break;
				default :
					System.out.println("Opcao invalida!");
			}
		}
		
	}

	/**
	 * Método que cria o menu para manutenção de usuários e chama as funções necessárias conforme a escolha do usuário.
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
	 * Método que cria o menu para manutenção e produtos e chama as funções necessárias conforme a escolha do usuário.
	 * @param logged O usuário que está logado.
	 */
	private static void admProd(User logged) {
		int opt = 0;
		while(opt!=5)
		{
			System.out.println("1 - Adicionar item");
			System.out.println("2 - Remover item");
			System.out.println("3 - Editar item");
			System.out.println("4 - Exibir itens");
			System.out.println("5 - Voltar");
			opt = keyboard.nextInt();
			switch(opt)
			{
				case 1 :
					createItem();
					break;
				case 2 :
					removeItem();
					break;
				case 3 :
					editItem();
					break;
				case 4 :
					Item.printAll();
					break;
				case 5 :
					break;
				default :
					System.out.println("Opcao invalida!");
			}
		}
	}

	/**
	 * Método que verifica se o usuário quer editar o preço ou o nome do item e direciona para o setter correspondente.
	 */
	private static void editItem() {
		int opt = 0;
		String buffer;
		System.out.println("Digite o codigo do item que quer editar:");
		buffer = keyboard.nextLine();
		Item i = Item.findItem(buffer);
		if(i!=null)
		{
			while(opt!=3)
			{
				System.out.println("1 - Editar nome");
				System.out.println("2 - Editar preço");
				System.out.println("3 - Voltar");
				opt = keyboard.nextInt();
				switch(opt)
				{
					case 1 :
						System.out.println("Digite o novo nome:");
						buffer = keyboard.nextLine();
						i.setName(buffer);
						break;
					case 2 :
						System.out.println("Digite o novo preco:");
						buffer = keyboard.nextLine();
						i.setPrice(Double.parseDouble(buffer));
						break;
					case 3 :
						break;
					default :
						System.out.println("Opcao invalida!");
				}
			}
		}
		
	}

	/**
	 * Método que remove um item e as transações que o envolvem.
	 */
	private static void removeItem() {
		// TODO Remove um item e as transações que o envolvem.
		
	}

	/**
	 * Método que verifica qual tipo de item o usuário quer criar e chama o addItem da classe.
	 */
	private static void createItem() {
		int i = 0;
		System.out.println("Selecione o tipo de item que quer criar:");
		for(String s : Item.vItemClass)
		{
			System.out.println(i +" - "+s);
		}
		i = keyboard.nextInt();
		try {
			Class<?> c = Class.forName(Item.vItemClass.get(i));
			Item item = (Item) c.newInstance();
			item.addItem();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro! Contate o desenvolvedor.");
		}
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