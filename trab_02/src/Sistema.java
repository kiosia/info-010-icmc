import java.util.*;

public class Sistema {

	public static void loadFiles() {
		// abre e carrega arquivo de usuários
		// se não houver, cria o arquivo, cadastra
		// um usuário padrão e avisa ao usuário.
		// user: admin senha: admin
		
		// abre e carrega arquivo de itens
		// se não houver, cria o arquivo e avisa
		// ao usuário

		// abre e carrega arquivo de transações
		// se não houver, cria o arquivo e avisa
		// ao usuário
	}
	
	
	/**
	 * TENTAR FAZER COM VECTOR!!!
	 * VERIFICAR COMO FAZER DINÂMICO!!!
	 */
	public static User[] vUsers = new User[5000]; // vetor que guarda os usuários cadastrados no sistema;
	public static int qttUsers = 0; // contador para a quantidade de usuários cadastrados no sistema;
	
	/**
	 * Método que verifica se o usuário existe no vetor de usuários.
	 * @return O usuário, caso exista, e null caso não exista.
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
	 * Método para codificar a senha.
	 * (Ainda não implementada.)
	 * @param pass A senha original.
	 * @return A senha codificada. 
	 */
	private static String encode(String pass) {
		return pass;
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
		Scanner keyboard = new Scanner(System.in);
		String username, password;
		msg("Sistema de estoque\n");
		if(qttUsers==0)
		{ // se não existir nenhum usuário, cadastra o admin
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