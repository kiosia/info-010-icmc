import java.util.*;
import java.io.File;
import java.io.IOException;

public class Sistema {

	public static void loadFiles() {
			try {
				Scanner inFile = new Scanner(new File("usr.txt"));
				while(inFile.hasNext()) {
					msg("leu");
					String name = inFile.nextLine();
					String username = inFile.nextLine();
					String password = inFile.nextLine();
					boolean adm = inFile.nextLine().equals(1);
					User user = new User(name, username, password, adm);
					vUsers.add(user);
				}
			} catch (IOException e) {
				System.err.println("Erro ao ler o arquivo de usuarios!");
			}
	
		// abre e carrega arquivo de itens
		// se n�o houver, cria o arquivo e avisa
		// ao usu�rio

		// abre e carrega arquivo de transa��es
		// se n�o houver, cria o arquivo e avisa
		// ao usu�rio
	}
	
	public static void saveFiles ()
	{
		
	}
	
	
	public static Vector<User> vUsers = new Vector<User>(); // Vetor que guarda os usu�rios cadastrados no sistema;
	public static Scanner k = new Scanner(System.in);
	
	
	/**
	 * M�todo que verifica se o usu�rio existe no vetor de usu�rios.
	 * @return O usu�rio, caso exista, e null caso n�o exista.
	 */
	public static User log(String username, String password){
		User aux;
		for(int i=0;i<vUsers.size();i++)
		{
			aux = vUsers.elementAt(i);
			if(username.equals(aux.getUsername()))
			{
				if(password.equals(aux.getPassword()))
				{
					return aux;
				}
				else
					msg("Senha incorreta!");
			}
			else
				msg("Este usuario nao existe!");
		}
		return null;
	}
	
	/**
	 * M�todo para codificar a senha.
	 * (Ainda n�o implementada.)
	 * @param pass A senha original.
	 * @return A senha codificada. 
	 */
	private static String encode(String pass) {
		return pass;
	}
	
	/**
	 * M�todo da pregui�a de escrever System.out.println() toda vez.
	 * @param message A mensagem que se quer escrever na tela.
	 */
	public static void msg(String message) {
		System.out.println(message);
	}
	
	/**
	 * @param args Vetor de argumentos passados via console
	 */
	public static void main(String[] args) {
		loadFiles();
		String username, password, opt;
		User aux; 
		msg("Sistema de estoque\n");
		if(vUsers.size()==0) // se n�o existir nenhum usu�rio, cadastra o admin
		{ 
			aux = new User("Administrador", "admin", encode("admin"), true);
			vUsers.add(aux);
			msg("Administrador criado! Username: admin Password: admin");
			msg("Troque sua senha assim que possivel!");
		}
		while(true)
		{
			msg("Digite seu nome de usuario:");
			username = k.next();
			msg("Digite sua senha:");
			password = k.next();
			aux = log(username, encode(password)); 
			if(aux!=null)
			{
				mainMenu(aux);
			}
			msg("Deseja sair do programa?(S/N)");
			opt = k.next();
			if((opt.equals("s"))||(opt.equals("S")))
			{
				msg("Obrigado por utilizar este sistema!");
				saveFiles();
				return;
			}
			saveFiles();
		}
	}

	private static void mainMenu(User logged) {
		int opt = 0;
		msg("Bem vindo "+logged.getName()+"!");
		if(logged.getAdm()) // faz o menu caso o usu�rio seja um administrador
		{
			while(opt!=4)
			{
				msg("1 - Gerenciar produtos");
				msg("2 - Gerenciar usu�rios");
				msg("3 - Gerar relat�rios");
				msg("4 - Logout");
				msg("5 - Sair");
				opt = k.nextInt();
				switch(opt)
				{
					case 1 :
						manProd(logged);
						break;
					case 2 :
						manUser(logged);
						break;
					case 3 :
						genReport(logged);
						break;
					case 4 :
						break;
					case 5 :
						msg("Obrigado por utilizar este sistema!");
						saveFiles();
						System.exit(0);
					default :
						msg("Opcao invalida!");
				}
			}
		}
		else // faz o menu para usu�rios comuns
		{
			while(opt!=3)
			{
				msg("1 - Transacoes");
				msg("2 - Trocar senha");
				msg("3 - Logout");
				msg("4 - Sair");
				opt = k.nextInt();
				switch(opt)
				{
					case 1 :
						manProd(logged);
						break;
					case 2 :
						edtPass(logged);
						break;
					case 3 :
						break;
					case 4 :
						msg("Obrigado por utilizar este sistema!");
						saveFiles();
						System.exit(0);
					default :
						msg("Opcao invalida!");
				}
			}			
		}
	}

	private static void genReport(User logged) {
		// TODO Auto-generated method stub
		
	}

	private static void edtPass(User logged) {
		String pass, pass2;
		msg("Digite sua senha atual:");
		pass = k.next();
		pass = encode(pass);
		if(!pass.equals(logged.getPassword()))
		{
			msg("Senha incorreta!");
			return;
		}
		else
		{
			msg("Digite sua nova senha:");
			pass = k.next();
			msg("Digite novamente:");
			pass2 = k.next();
			if(pass.equals(pass2))
			{
				logged.setPassword(encode(pass));
				msg("Senha alterada com sucesso!");
			}
			else
			{
				msg("As senhas digitadas nao sao a mesma!");
			}
		}
	}

	private static void manUser(User logged) {
		// TODO Auto-generated method stub
		edtPass(logged);
		
	}

	private static void manProd(User logged) {
		// TODO Auto-generated method stub
		
	}
}