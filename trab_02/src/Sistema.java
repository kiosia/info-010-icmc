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
	
	
	/**
	 * TENTAR FAZER COM VECTOR!!!
	 * VERIFICAR COMO FAZER DIN�MICO!!!
	 */
	public static User[] vUsers = new User[5000]; // vetor que guarda os usu�rios cadastrados no sistema;
	public static int qttUsers = 0; // contador para a quantidade de usu�rios cadastrados no sistema;
	
	/**
	 * M�todo que verifica se o usu�rio existe no vetor de usu�rios.
	 * @return O usu�rio, caso exista, e null caso n�o exista.
	 */
	public static User log(String username, String password){
		User aux;
		for(int i=0;i<qttUsers;i++)
		{
			aux = vUsers[i];
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
		Scanner keyboard = new Scanner(System.in);
		String username, password;
		msg("Sistema de estoque\n");
		if(qttUsers==0)
		{ // se n�o existir nenhum usu�rio, cadastra o admin
			User aux = new User("Administrador", "admin", encode("admin"), true);
			vUsers[qttUsers++] = aux;
			msg("Administrador criado! Username: admin Password: admin");
			msg("Troque sua senha assim que possivel!");
		}
		msg("Digite seu nome de usuario:");
		username = keyboard.next();
		msg("Digite sua senha:");
		password = keyboard.next();
		if(log(username, encode(password))!=null)
			mainMenu();
	}

	private static void mainMenu() {
		msg("Sistema de estoque");
	}
}