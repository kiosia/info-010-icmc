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
	 * @param args Vetor de argumentos passados via console
	 */
	public static void main(String[] args) {
		loadFiles();
		Scanner keyboard = new Scanner(System.in);
		String username, password;
		System.out.println("Sistema de estoque\n");
		System.out.println("Digite seu nome de usuario:\n");
		username = keyboard.next();
		System.out.println("Digite sua senha:\n");
		password = keyboard.next();
		User aux = new User(username, password);
		if(aux.log()!=null)
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