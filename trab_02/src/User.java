import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.*;
import java.util.*;

/**
 * Classe que define os usu�rios do sistema.
 * @author Al�xis Kiosia (7152097)
 * @since 21/05/2011
 */
public class User {

	/**
	 * Define o nome real do usu�rio.
	 */
	private String name;
	/**
	 * Define o n�vel de privil�gio do usu�rio.
	 */
	private boolean adm; 
	/**
	 * Define a senha de acesso do usu�rio.
	 */
	private String password;
	/**
	 * Define o login do usu�rio.
	 */
	private String username;

	/**
	 * Vetor que cont�m os usu�rios cadastrados.
	 */
	public static Vector<User> vUsers = new Vector<User>();	

	/**
	 * Vari�vel para leitura do teclado.
	 */
	private static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * Construtor para usu�rios que est�o tentando logar no sistema.
	 * @param password O password que o usu�rio digitou.
	 * @param username O username que o usu�rio digitou.
	 */
	public User(String username, String password) {
		this.password = encode(password, username);
		this.username = username;
	}	
	
	/**
	 * Construtor que recebe todos os campos mais um par�metro que indica se o password deve ser criptografado.
	 * @param name define o nome real do usu�rio.
	 * @param password define a senha do usu�rio.
	 * @param username define o login do usu�rio.
	 * @param adm define se o usu�rio � administrador ou n�o.
	 * @param fromFile define se o password veio do arquivo, ou seja, j� est� criptografado.
	 */
	public User(String name, String username, String password, boolean adm, boolean fromFile) {
		if(!fromFile)
		{
			password = encode(password, username);
		}
		this.name = name;
		this.username = username;
		this.password = password;
		if(this.username.equals("admin")) // dessa forma, o usu�rio admin � SEMPRE administrador, mesmo que o arquivo de usu�rios seja editado.
		{
			this.adm = true;
		}
		this.adm = adm;
	}
	
	/**
	 * Getter para o nome.
	 * @return String que cont�m o nome do usu�rio em quest�o.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter para o username.
	 * @return String que cont�m o username do usu�rio em quest�o.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Getter para o password.
	 * @return String que cont�m o password do usu�rio em quest�o.
	 */
	public String getPassword() {
		return this.password;
	}	
	
	/**
	 * Getter para adm.
	 * @return Vari�vel do tipo boolean que cont�m o adm do usu�rio em quest�o. 
	 */
	public boolean getAdm() {
		return adm;
	}

	/**
	 * Setter para adm.
	 * @param adm Vari�vel do tipo boolean que cont�m o novo adm. 
	 */
	private void setAdm(boolean adm) {
		this.adm = adm;
	}	

	/**
	 * M�todo que carrega os usu�rios do arquivo.
	 * @return Vari�vel do tipo boolean que diz se o arquivo de usu�rios foi criado do zero (true caso criado).
	 */
	
 	public static boolean loadFile() {
 		boolean newSystem = false;
 		try
		{
			File aux = new File("usr");
			if (aux.exists())
			{
				String name, username, password;
				boolean adm;
				User user;
				Scanner inFile = new Scanner(aux);
				while(inFile.hasNext())
				{
					name = inFile.nextLine();
					username = inFile.nextLine();
					password = inFile.nextLine();
					adm = inFile.nextLine().equals("1");
					user = new User(name, username, password, adm, true);
					User.vUsers.add(user);
				}
				inFile.close();
			}
			else
			{
				aux.createNewFile();
				newSystem = true; // Se n�o houver arquivo de usu�rios, o sistema est� comprometido. Refaz-se TODOS os arquivos.
				System.out.println("Arquivo de usuarios criado!");
			}
		}
		catch (Exception e)
		{
			System.err.println("Erro na leitura do arquivo de usuarios!");
		}
		return newSystem;
 	}

	/**
	 * M�todo que salva o vetor de usu�rios para o arquivo.
	 */
 	public static void saveFile() {
		try
		{
			PrintWriter out = new PrintWriter(new FileWriter("usr"));
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
 	}
 	
	/**
	 * M�todo para trocar o password do usu�rio.
	 * @param needPass Vari�vel do tipo boolean que verifica se o usu�rio necessita do password atual para trocar sua senha.
	 */
 	public void edtPass(boolean needPass) {
 		String pass, pass2;
 		if(needPass)
 		{
			System.out.println("Digite sua senha atual:");
			pass = keyboard.next();
			pass = User.encode(pass, this.username);
			if(!pass.equals(password))
			{
				System.out.println("Senha incorreta!");
				return;
			}
 		}
 		while(true)
 		{
 			System.out.println("Digite sua nova senha:");
 			pass = keyboard.next();
 			System.out.println("Digite novamente:");
 			pass2 = keyboard.next();
 			if(pass.equals(pass2))
 			{
 				this.password = encode(pass, this.username);
 				System.out.println("Senha alterada com sucesso!");
 				return;
 			}
 			else
 			{
 				System.out.println("As senhas digitadas nao sao as mesmas!");
 			}
 		}
	}
	
	/**
	 * M�todo para codificar a senha. A senha codificada � composta pelo nome do usu�rio
	 * concatenada com a senha em si, para que caso o arquivo de usu�rios seja editado com
	 * m�s inten��es, as senhas sejam inutilizadas. Ainda h� a possibilidade de o 'usu�rio'
	 * editar o status de administrador, mas pelo menos as senhas est�o seguras.
	 * Fonte: http://codare.net/2007/02/02/java-gerando-codigos-hash-md5-sha/
	 * @param pass A senha original.
	 * @param user O username, ser� usado para compor a senha codificada.
	 * @return A senha codificada. 
	 */
	public static String encode(String pass, String user) {
		byte[] aux;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(user);
		stringBuilder.append(pass);
		String concat = stringBuilder.toString();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(concat.getBytes());
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
	 * M�todo para logar o usu�rio.
	 * @return Objeto do tipo User que cont�m o usu�rio, caso exista, e null caso n�o exista ou a senha esteja incorreta.
	 */
	public static User log(String username, String password){
		User user = findUser(username);
		if(user!=null)
		{
			password = encode(password, username);
			if(password.equals(user.password))
			{
				return user;
			}
			else
			{
				System.out.println("Senha incorreta!");
				return null;
			}
		}
		return null;
	}
	
	/**
	 * M�todo que procura por um usu�rio no vetor de usu�rios.
	 * @param userName O username do usu�rio que se quer procurar.
	 * @return Objeto do tipo User que cont�m o usu�rio encontrado ou null se n�o encontrar nenhum.
	 */
	public static User findUser(String userName) {

			for(int i=0;i<vUsers.size();i++)
			{
				if(vUsers.get(i).username.equals(userName))
				{
					return vUsers.get(i);
				}
			}
			System.out.println("Usuario nao encontrado!");
			return null;
		}

	/**
	 * M�todo que imprime na tela os dados de todos os usu�rios.
	 */
	public static void printAll() {
		for(User u: vUsers)
			u.print();
		System.out.println("Existe(m) "+vUsers.size()+" usuario(s)!");
	}

	/**
	 * M�todo que imprime na tela os dados do usu�rio em quest�o.
	 */
	private void print() {
		String aux = "Nao";
		if(this.adm)
			aux = "Sim";
		System.out.println("Nome: "+this.name+" Username: "+this.username+" Adm: "+aux);
	}

	/**
	 * M�todo que coleta dados e altera o status de adm de um usu�rio.
	 * @param logged Objeto que representa o usu�rio que est� logado.
	 */
	public static void edtAdm(User logged) {
		keyboard.nextLine();
		System.out.println("Digite o username do usuario a ser alterado:");
		User u = User.findUser(keyboard.nextLine());
		if(logged.username.equals(u.getUsername()))
		{
			System.out.println("Impossivel alterar: usuario logado");
			return;
		}
		if(u!=null)
		{
			String opt;
			if(u.getAdm())
			{
				System.out.println("Usuario atualmente eh admin. Deseja mudar?(S/N)");
				opt = keyboard.nextLine(); 
				if(opt.toUpperCase().equals("S"))
					u.setAdm(false);
			}
			else
			{
				System.out.println("Usuario atualmente nao eh admin. Deseja mudar?(S/N)");
				opt = keyboard.nextLine(); 
				if(opt.toUpperCase().equals("S"))
					u.setAdm(true);
			}
		}
	}

	/**
	 * M�todo que coleta dados e adiciona um usu�rio.
	 */
	public static void addUser() {
		String name, username, password;
		int opt;
		boolean adm;
		System.out.println("Digite o nome do usuario:");
		name = keyboard.nextLine();
		System.out.println("Digite o username do usuario:");
		username = keyboard.nextLine();
		System.out.println("Digite o password do usuario:");
		password = keyboard.nextLine();
		System.out.println("Digite 1 para criar um administrador e 0 para criar um usuario:");
		opt = keyboard.nextInt();
		if(opt==1)
			adm = true; 
		else
			adm = false;
		User u = new User(name, username, password, adm, false);
		vUsers.add(u);
	}

	/**
	 * M�todo que coleta dados e remove um usu�rio.
	 * As transa��es relacionadas a este usu�rio s�o transferidas
	 * ao respons�vel pela exclusao.
	 * @param logged Objeto que representa o usu�rio que est� logado.
	 */
	public static void delUser(User logged) {
		String username, opt;
		System.out.println("Digite o username do usuario que deseja excluir:");
		username = keyboard.nextLine();
		if(logged.username.equals(username))
		{
			System.out.println("Impossivel excluir: usuario logado");
			return;
		}
		User u = User.findUser(username);
		if(u!=null)
		{
			System.out.println("As transacoes relacionadas a este usuario irao para o seu nome. Deseja continuar?(S/N)");
			opt = keyboard.nextLine();
			if(opt.toUpperCase().equals("S"))
			{
				for(Transaction t: Transaction.vTransactions)
					if(t.getUsername().equals(username))
						t.setUsername(username);
				vUsers.remove(u);
				System.out.println("Usuario excluido com sucesso!");
			}
		}
	}

}