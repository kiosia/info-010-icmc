import java.util.*;

public class Sistema {

	public static void loadFiles() {
		// abre e carrega arquivo de usu�rios
		// se n�o houver, cria o arquivo, cadastra
		// um usu�rio padr�o e avisa ao usu�rio.
		// user: admin senha: admin
		
		// abre e carrega arquivo de itens
		// se n�o houver, cria o arquivo e avisa
		// ao usu�rio

		// abre e carrega arquivo de transa��es
		// se n�o houver, cria o arquivo e avisa
		// ao usu�rio
	}
	
	public static User[] vUsers = null;
	public static int qttUsers = 0;
	
	/**
	 * M�todo que verifica se o usu�rio existe no vetor
	 * de usu�rios.
	 * @return O usu�rio, caso exista, e null caso n�o exista.
	 */
	public static User log(String username, String password){
		User aux;
		for(int i=0;i<qttUsers;i++)
		{
			aux = vUsers[i];
			if((username==aux.getUsername())&&(password==aux.getPassword()))
				return aux;
		}
		return null;
	}
	
//	/**
//	 * M�todo para codificar a senha.
//	 * (Ainda n�o implementada.)
//	 * @param pass A senha original.
//	 * @return A senha codificada. 
//	 */
//	private static String encode(String pass) {
//		return pass;
//	}
	
	public static void msg(String message){
		System.out.println(message);
	}
	
	/**
	 * @param args Vetor de argumentos passados via console
	 */
	public static void main(String[] args) {
		loadFiles();
		Scanner keyboard = new Scanner(System.in);
		String username, password;
		System.out.println("Sistema de estoque\n");
		msg("Ha "+qttUsers+" usuario(s) cadastrado(s)!");
		if(qttUsers==0)
		{ // se n�o existir nenhum usu�rio, cadastra o admin
			User aux = new User("Administrador", "admin", "admin", true);
			vUsers[qttUsers] = aux;
			qttUsers++;
			System.out.println("Administrador criado! Username: admin Password: admin\n");
			System.out.println("Troque sua senha assim que possivel!\n");
		}
		System.out.println("Digite seu nome de usuario:\n");
		username = keyboard.next();
		System.out.println("Digite sua senha:\n");
		password = keyboard.next();
		if(log(username, password)!=null)
		{
			int option = -1;
			while(option!=3)
			{
				System.out.println("Sistema de estoque\n");
				System.out.println("Sistema de estoque\n");
				System.out.println("Sistema de estoque\n");
			}
		}
	}
}