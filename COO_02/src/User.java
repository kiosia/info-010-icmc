import java.util.Vector;
import java.security.MessageDigest;

/**
 * @author Aléxis Kiosia (7152097)
 * @since 21/05/2011
 */
public class User {

	private static Vector<User> vUser;
	
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
	 * Construtor que não recebe nível de usuário como parâmetro.
	 * e retorna um usuário sem privilégios administrativos.
	 * @param name define o nome real do usuário.
	 * @param password define a senha do usuário.
	 * @param username define o login do usuário.
	 */	
	public User(String name, String password, String username) {
		this(name, password, username, false);
	}

	/**
	 * Construtor para usuários que estão tentando logar no sistema.
	 * @param password O password que o usuário digitou.
	 * @param username O username que o usuário digitou.
	 */
	public User(String username, String password) {
		this.name = null;
		this.password = encode(password);
		this.username = username;
		this.adm = (Boolean) null;
	}	
	
	/**
	 * Construtor que recebe nível de usuário como parâmetro.
	 * @param name define o nome real do usuário.
	 * @param password define a senha do usuário.
	 * @param username define o login do usuário.
	 * @param level define o nivel de privilégios do usuário.
	 */
	public User(String name, String password, String username, boolean adm) {
		this.name = name;
		this.password = password;
		this.username = username;
		this.adm = adm;
		vUser.add(this);
	}	
	/**
	 * Método para codificar a senha.
	 * (Ainda não implementada.)
	 * @param pass A senha original.
	 * @return A senha codificada. 
	 */
	private String encode(String pass) {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] bytes = md.digest();
		StringBuilder s = new StringBuilder();
		   for (int i = 0; i < bytes.length; i++) {
		       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		       int parteBaixa = bytes[i] & 0xf;
		       if (parteAlta == 0) s.append('0');
		       s.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return s.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public User log(){
		User aux;
		for(int i=0;i<vUser.size();i++)
		{
			aux = vUser.elementAt(i); 
			if((aux.username == this.username) && (aux.password == encode(this.password)))
				return aux;
		}
		return null;
	}

}