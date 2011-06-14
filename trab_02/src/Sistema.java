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
	
	public static User[] vUsers = new User[5000];
	public static int qttUsers = 0;
	
	/**
	 * Método que verifica se o usuário existe no vetor
	 * de usuários.
	 * @return O usuário, caso exista, e null caso não exista.
	 */
	public static User log(String username, String password){
		User aux;
		msg("User: "+username+" Pass: "+password );
		for(int i=0;i<qttUsers;i++)
		{
			
			aux = vUsers[i];
			msg("User: "+aux.getUsername()+" Pass: "+aux.getPassword());
			
			if(username.equals(aux.getUsername()))
			{
				msg("Usuario existe!");
				if(password.equals(aux.getPassword()))
				{
					msg("Senha correta!");
					return aux;
				}
			}

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
		{ // se não existir nenhum usuário, cadastra o admin
			User aux = new User("Administrador", "admin", encode("admin"), true);
			vUsers[qttUsers++] = aux;
			System.out.println("Administrador criado! Username: admin Password: admin\n");
			System.out.println("Troque sua senha assim que possivel!\n");
		}
		System.out.println("Digite seu nome de usuario:\n");
		username = keyboard.next();
		System.out.println("Digite sua senha:\n");
		password = keyboard.next();
		if(log(username, encode(password))!=null)
		{
			int option = -1;
			//while(option!=3)
			//{
				System.out.println("Sistema de estoque\n");
				System.out.println("Sistema de estoque\n");
				System.out.println("Sistema de estoque\n");
			//}
		}
		else
			msg("Usuario nao encontrado!\n");
	}
}