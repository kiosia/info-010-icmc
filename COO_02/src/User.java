import java.util.Vector;
import java.security.MessageDigest;

/**
 * @author Al�xis Kiosia (7152097)
 * @since 21/05/2011
 */
public class User {

	private static Vector<User> vUser;
	
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
	 * Construtor que n�o recebe n�vel de usu�rio como par�metro.
	 * e retorna um usu�rio sem privil�gios administrativos.
	 * @param name define o nome real do usu�rio.
	 * @param password define a senha do usu�rio.
	 * @param username define o login do usu�rio.
	 */	
	public User(String name, String password, String username) {
		this(name, password, username, false);
	}

	/**
	 * Construtor para usu�rios que est�o tentando logar no sistema.
	 * @param password O password que o usu�rio digitou.
	 * @param username O username que o usu�rio digitou.
	 */
	public User(String username, String password) {
		this.name = null;
		this.password = encode(password);
		this.username = username;
		this.adm = (Boolean) null;
	}	
	
	/**
	 * Construtor que recebe n�vel de usu�rio como par�metro.
	 * @param name define o nome real do usu�rio.
	 * @param password define a senha do usu�rio.
	 * @param username define o login do usu�rio.
	 * @param level define o nivel de privil�gios do usu�rio.
	 */
	public User(String name, String password, String username, boolean adm) {
		this.name = name;
		this.password = password;
		this.username = username;
		this.adm = adm;
		vUser.add(this);
	}	
	/**
	 * M�todo para codificar a senha.
	 * (Ainda n�o implementada.)
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