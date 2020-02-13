import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AulaApplication {

	
	private static final String COMMA_DELIMITER = ",";
	public String nome;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("Hello World");
		// exercicioA();
		// exercicioB();
		// exercicio3();
		// exercicio5();
		// exercicio6();
		// exercicio7();
		//calcularSalarioLiquido(1032);
		//lerArquivo();
		//q1();
		//q2();
		//q3();
		q4();
	}
	
	
	private static List<String> getRecordFromLine(String line) {
	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(COMMA_DELIMITER);
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
	
	public static List<List<String>> lerArquivo() {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("/home/juliana/codenation/java-3/src/main/resources/data_original.csv"));) {
		    while (scanner.hasNextLine()) {
		        records.add(getRecordFromLine(scanner.nextLine()));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
	
	// Quantas nacionalidades (coluna `nationality`14) diferentes existem no arquivo?
	public static int q1() {
		List<List<String>> registrosCSV = lerArquivo();
		
		List<String> nacionalidade = new ArrayList<>();

		for (int i = 1;i<registrosCSV.size();i++) {
			if(!nacionalidade.contains(registrosCSV.get(i).get(14))) {
				nacionalidade.add(registrosCSV.get(i).get(14));
			}
		}

		return nacionalidade.size();
	}

	// Quantos clubes (coluna `club`3) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public static int q2() {
		List<List<String>> registrosCSV = lerArquivo();
		
		List<String> clubes = new ArrayList<>();

		for (int i = 1;i<registrosCSV.size();i++) {
			if(!clubes.contains(registrosCSV.get(i).get(3))) {
				clubes.add(registrosCSV.get(i).get(3));
			}
		}
		System.out.println(clubes.size());
		return clubes.size();
	}

	// Liste o nome completo (coluna `full_name`2) dos 20 primeiros jogadores.
	public static List<String> q3() {
		List<List<String>> registrosCSV = lerArquivo();
		
		List<String> nome20 = new ArrayList<>();

		for (int i = 1;i<21;i++) {
			nome20.add(registrosCSV.get(i).get(2));
		}
		
		return nome20;
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name`2 e `eur_release_clause`18)
	public static List<String> q4() {
		List<List<String>> registrosCSV = lerArquivo();
		
		Map<String, Float> listaReduzida = new HashMap<String, Float>();
		
		for (int i = 1;i<registrosCSV.size();i++) {
			String vNome = registrosCSV.get(i).get(2);
			String vRescisao = registrosCSV.get(i).get(18);
			if (vRescisao.isEmpty()) {
				vRescisao = "0";
			}
			
			listaReduzida.put(vNome, Float.valueOf(vRescisao));
		}
		
		final Map<String, Float> listaOrdenada = listaReduzida.entrySet()
				.stream()
				//.sorted(Map.Entry.<String, Double>comparingByValue().reversed())
				.sorted(Map.Entry.<String, Float>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey,  Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		listaOrdenada.forEach((key, value) -> System.out.println(value + " - " + key));
		

		return null;
	}
	

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`19)?
	// (utilize as colunas `full_name`2 e `birth_date`8)
	public static List<String> q5() {
		return null;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`6)
	public static Map<Integer, Integer> q6() {
		return null;
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////

	public static void exercicioA() {
		int a;
		int b;

		a = 10;
		b = 20;

		System.out.println("b: " + b);

		b = 5;

		System.out.println("a: " + a + " b: " + b);

	}

	public static void exercicioB() {
		int a;
		int b;
		int c;

		a = 30;
		b = 20;
		c = a + b;

		System.out.println("c: " + c);

		b = 10;

		System.out.println("b: " + b + " c: " + c);

		c = a + b;

		System.out.println("a: " + a + " b: " + b + " c: " + c);
	}

	public static void exercicio3() {
		float a;
		a = (4 / 2) + (2 / 4);
		System.out.println(a);
		a = 4 / 2 + 2 / 4;
		System.out.println(a);
	}

	public static void exercicio5() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Qual a letra: ");
		String nome = scanner.next();

		System.out.println((char) (nome.charAt(0) - 1));
	}

	public static void exercicio6() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Lado 1: ");
		int lado1 = scanner.nextInt();
		System.out.print("Lado 2: ");
		int lado2 = scanner.nextInt();

		System.out.println("Área: " + (lado1 * lado2));
	}

	public static void exercicio7() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Anos: ");
		int anos = scanner.nextInt();
		System.out.print("Meses: ");
		int meses = scanner.nextInt();
		System.out.print("Dias: ");
		int dias = scanner.nextInt();

		System.out.println("Idade em dias: " + ((anos * 365) + (meses * 30) + dias));
	}

	public static void calcularSalarioLiquido(double salarioBase) {
		// Recebe salário bruto e aplica descontos de INSS e IRPF, retornando o salário
		// líquido
		// Para salários abaixo de 1039.00, retornar 0.0
		double salarioDescontoINSS = 0;
		double salarioLiquido = 0;
		

		if (salarioBase < 1039.00) {
			salarioLiquido = 0.00;
		} else {
			// Aplica desconto INSS
			salarioDescontoINSS = calcularDescontoInss(salarioBase);
			System.out.println("salario com desconto INSS: " + salarioDescontoINSS);
	
			// Aplica desconto IRPF
			salarioLiquido = calcularDescontoIrpf(salarioDescontoINSS);
			System.out.println("salario com desconto INSS e IRPF: " + salarioLiquido);
		}
		
		
		System.out.println(Math.round(salarioLiquido));
		//return Math.round(0.0);

	}

	public static double calcularDescontoInss(double salarioBase) {
		// Recebe o valor bruto e aplica o desconto do INSS, seguindo a tabela:
		// Até 1.500,00 --> 8% de desconto
		// De 1.500,01 a 4.000,00 --> 9% de desconto
		// Acima de 4.000,00 --> 11% de desconto
		double aliquota = 0;
		if (salarioBase <= 1500.00) {
			aliquota = 8;
		} else if ((salarioBase > 1500.00) && (salarioBase <= 4000.00)) {
			aliquota = 9;
		} else {
			aliquota = 11;
		}
		return (salarioBase * (1 - (aliquota / 100)));

	}

	public static double calcularDescontoIrpf(double salarioInss) {
		// Recebe o valor com desconto do INSS e aplica o desconto do IRPF, seguindo a
		// tabela:
		// Até 3.000,00 --> ISENTO
		// De 3.000,01 a 6.000,00 --> 7.5% de desconto
		// Acima de 6.000,00 --> 15% de desconto
		double aliquota = 0;
		if (salarioInss <= 3000.00) {
			aliquota = 0;
		} else if ((salarioInss > 3000.00) && (salarioInss <= 6000.00)) {
			aliquota = 7.5;
		} else {
			aliquota = 15;
		}
		return (salarioInss * (1 - (aliquota / 100)));
	}
}
