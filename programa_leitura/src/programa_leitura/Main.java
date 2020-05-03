package programa_leitura;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main( String[] string) {
		Scanner in = new Scanner(System.in);
		String nome, idade, peso, cidade, estado, pais;
		
		System.out.println("Por favor, digite o seu nome:");
		nome = in.nextLine();
		
		System.out.println("Por favor, digite o sua idade:");
		idade = in.nextLine();
		
		System.out.println("Por favor, digite o seu peso:");
		peso = in.nextLine();
		
		System.out.println("Por favor, digite o sua cidade:");
		cidade = in.nextLine();
		
		System.out.println("Por favor, digite o seu estado:");
		estado = in.nextLine();
		
		System.out.println("Por favor, digite o seu país:");
		pais = in.nextLine();
		
		Random rand = new Random();
		int lucky_number = rand.nextInt(100);
		
		// Gerar resultado final
		System.out.println("-----------------------------");
		System.out.println("Aqui está o resultado do teste:");
		System.out.println("Nome: "+nome);
		System.out.println("Peso: "+peso);
		System.out.println("Cidade: "+cidade);
		System.out.println("Estado: "+estado);
		System.out.println("Pais: "+pais);
		System.out.println(lucky_number);
	}
}
