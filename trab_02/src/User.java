import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Aléxis Kiosia (7152097)
 * @since 21/05/2011
 */
public class User {

	/**
	 * Define o nome real do usuário.
	 */
	private String name;
	/**
	 * Define o nível de privilégio do usuário.
	 */
	private boolean adm; 
	/**
	 * Define a senha de acesso do usuário.
	 */
	private String password;
	/**
	 * Define o login do usuário.
	 */
	private String username;

	/**
	 * Vetor que contém os usuários cadastrados.
	 */
	public static Vector<User> vUsers = new Vector<User>();	

	/**
	 * Variável para leitura do teclado.
	 */
	private static Scanner k = new Scanner(System.in);
	
	/**
	 * Construtor para usuários que estão tentando logar no sistema.
	 * @param password O password que o usuário digitou.
	 * @param username O username que o usuário digitou.
	 */
	public User(String username, String password) {
		this.password = encode(password);
		this.username = username;
	}	
	
	/**
	 * Construtor para carregar do arquivo.
	 * @param name define o nome real do usuário.
	 * @param password define a senha do usuário.
	 * @param username define o login do usuário.
	 * @param level define o nivel de privilégios do usuário.
	 * @param fromFile define se o password veio do arquivo, ou seja, já está criptografado.
	 */
	public User(String name, String username, String password, boolean adm, boolean fromFile) {
		if(!fromFile)
		{
			password = encode(password);
		}
		this.name = name;
		this.username = username;
		this.password = password;
		this.adm = adm;
		vUsers.add(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}	
	
	public void setPassword(String password) {
		this.password = encode(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public boolean getAdm() {
		return adm;
	}

	/**
	 * Método para trocar o password do usuário.
	 */
	public void edtPass() {
		String pass, pass2;
		System.out.println("Digite sua senha atual:");
		pass = k.next();
		pass = User.encode(pass);
		if(!pass.equals(password))
		{
			System.out.println("Senha incorreta!");
			return;
		}
		else
		{
			System.out.println("Digite sua nova senha:");
			pass = k.next();
			System.out.println("Digite novamente:");
			pass2 = k.next();
			if(pass.equals(pass2))
			{
				setPassword(pass);
				System.out.println("Senha alterada com sucesso!");
			}
			else
			{
				System.out.println("As senhas digitadas nao sao as mesmas!");
			}
		}
	}	
	
	/**
	 * Método para codificar a senha.
	 * Fonte: http://codare.net/2007/02/02/java-gerando-codigos-hash-md5-sha/
	 * @param pass A senha original.
	 * @return A senha codificada. 
	 */
	public static String encode(String pass) {
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
	 * Método para logar o usuário.
	 * @return Objeto do tipo User que contém o usuário, caso exista, e null caso não exista ou a senha esteja incorreta.
	 */
	public static User log(String username, String password){
		User user = findUser(username);
		if(user!=null)
		{
			password = encode(password);
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
	 * Método que procura por um usuário no vetor de usuários.
	 * @param userName O username do usuário que se quer procurar.
	 * @return Objeto do tipo User que contém o usuário encontrado ou null se não encontrar nenhum.
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

}