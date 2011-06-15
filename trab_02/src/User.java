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
		this.password = encode(password);
		this.username = username;
	}	
	
	/**
	 * Construtor para carregar do arquivo.
	 * @param name define o nome real do usu�rio.
	 * @param password define a senha do usu�rio.
	 * @param username define o login do usu�rio.
	 * @param adm define se o usu�rio � administrador ou n�o.
	 * @param fromFile define se o password veio do arquivo, ou seja, j� est� criptografado.
	 */
	public User(String name, String username, String password, boolean adm, boolean fromFile) {
		if(!fromFile)
		{
			password = encode(password);
		}
		this.setName(name);
		this.username = username;
		this.password = password;
		this.adm = adm;
		vUsers.add(this);
	}
	
	/**
	 * Setter para o nome.
	 * @param name O novo nome.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Getter para adm.
	 * @return Vari�vel do tipo boolean que cont�m o adm do usu�rio em quest�o. 
	 */
	public boolean getAdm() {
		return adm;
	}

	/**
	 * M�todo para trocar o password do usu�rio.
	 */
	public void edtPass() {
		String pass, pass2;
		System.out.println("Digite sua senha atual:");
		pass = keyboard.next();
		pass = User.encode(pass);
		if(!pass.equals(password))
		{
			System.out.println("Senha incorreta!");
			return;
		}
		else
		{
			System.out.println("Digite sua nova senha:");
			pass = keyboard.next();
			System.out.println("Digite novamente:");
			pass2 = keyboard.next();
			if(pass.equals(pass2))
			{
				this.password = pass;
				System.out.println("Senha alterada com sucesso!");
			}
			else
			{
				System.out.println("As senhas digitadas nao sao as mesmas!");
			}
		}
	}	
	
	/**
	 * M�todo para codificar a senha.
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
	 * M�todo para logar o usu�rio.
	 * @return Objeto do tipo User que cont�m o usu�rio, caso exista, e null caso n�o exista ou a senha esteja incorreta.
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


}