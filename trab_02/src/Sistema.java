import java.util.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Sistema {

	// tirar o static!
	public static Vector<User> vUsers = new Vector<User>(); // Vetor que guarda os usuários cadastrados no sistema;
	public static Scanner k = new Scanner(System.in);
	
	public static void loadFiles() {
		try
		{
			Scanner inFile = new Scanner(new File("usr.txt"));
			while(inFile.hasNext())
			{
				String name = inFile.nextLine();
				String username = inFile.nextLine();
				String password = inFile.nextLine();
				boolean adm = inFile.nextLine().equals("1");
				User user = new User(name, username, password, adm);
				vUsers.add(user);
			}
		}
		catch (IOException e)
		{
			System.err.println("Erro ao ler o arquivo de usuarios!");
		}

	// abre e carrega arquivo de itens
	// se não houver, cria o arquivo e avisa
	// ao usuário

	// abre e carrega arquivo de transações
	// se não houver, cria o arquivo e avisa
	// ao usuário
}

	public static void saveFiles ()
	{
		
	}
	
	/**
	 * Método que verifica se o usuário existe no vetor de usuários.
	 * @return O usuário, caso exista, e null caso não exista.
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
				{
					msg("Senha incorreta!");
					return null;
				}
				
			}				
		}
		msg("Este usuario nao existe!");
		return null;
	}
	
	/**
	 * Método para codificar a senha.
	 * Fonte: http://codare.net/2007/02/02/java-gerando-codigos-hash-md5-sha/
	 * @param pass A senha original.
	 * @return A senha codificada. 
	 */
	private static String encode(String pass) {
		byte[] aux;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			aux = md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < aux.length; i++)
		{
			int parteAlta = ((aux[i] >> 4) & 0xf) << 4;
			int parteBaixa = aux[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}
	
	/**
	 * Método da preguiça de escrever System.out.println() toda vez.
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
		String username, password;
		User aux; 
		msg("\nSistema STOC - Stoc: Transacoes de Objetos Colossais\n");
		if(vUsers.size()==0) // se não existir nenhum usuário, cadastra o admin
		{ 
			aux = new User("Administrador", "admin", encode("admin"), true);
			vUsers.add(aux);
			msg("Primeiro acesso!\nAdministrador criado! Username: admin Password: admin");
			msg("Troque sua senha assim que possivel!\n");
		}
		while(true)
		{
			msg("Digite seu nome de usuario: (digite SAIR para sair)");
			username = k.next();
			if((username.equals("SAIR"))||(username.equals("sair")))
			{
				msg("Obrigado por utilizar o STOC!");
				saveFiles();
				return;
			}
			msg("Digite sua senha:");
			password = k.next();
			aux = log(username, encode(password)); 
			if(aux!=null)
			{
				mainMenu(aux);
			}
			saveFiles();
		}
	}

	private static void mainMenu(User logged) {
		int opt = 0;
		msg("Bem vindo "+logged.getName()+"!");
		if(logged.getAdm()) // faz o menu caso o usuário seja um administrador
		{
			while(opt!=4)
			{
				msg("1 - Gerenciar produtos");
				msg("2 - Gerenciar usuarios");
				msg("3 - Gerar relatorios");
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
						msg("Usuario "+logged.getUsername()+" deslogando...");
						break;
					case 5 :
						msg("Obrigado por utilizar o STOC!");
						saveFiles();
						System.exit(0);
					default :
						msg("Opcao invalida!");
				}
			}
		}
		else // faz o menu para usuários comuns
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
						msg("Usuario "+logged.getUsername()+" deslogando...");
						break;
					case 4 :
						msg("Obrigado por utilizar o STOC!");
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