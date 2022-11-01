package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Sale;

public class Program {

	private static final String Logan = null;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) { //abrindo o arquivo

			List<Sale> list = new ArrayList<>(); // Criando uma lista do tipo Sale

			String line = br.readLine(); 
			while (line != null) { // Enquanto itens forem diferentes de null faça.
				String[] fields = line.split(","); // Verificando cada item considerando que cada item está separado por ','.
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				//adicionando os itens do arquivo na lista tipo Sale.
				line = br.readLine();	
				}			
			Comparator<Sale> comp = ((s1, s2) -> s1.averagePrice().compareTo(s2.averagePrice())); // Comparando duas streams por ordem alfabética
						
			List<Sale> filter = (List<Sale>) list.stream()
					.filter(p -> p.getYear() ==2016)
					.sorted(comp.reversed())
					.limit(5)
					.collect(Collectors.toList());	
						
			Double log1 = list.stream()	
					.filter(p->p.getSeller().equals("Logan"))
					.filter(p->p.getMonth()==7)					
					.map(p-> p.getTotal())	
					.reduce(0.0, (x,y)-> x+y);
			
			Double log2 = list.stream()
					.filter(p->p.getSeller().equals("Logan"))					
					.filter(p->p.getMonth()==1)					
					.map(p-> p.getTotal())	
					.reduce(0.0, (x,y)-> x+y);
			
			double logan = log1 + log2;
							
			
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio: " );
			System.out.println(filter);

			System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + logan);
			
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

	private static Comparator<? super Sale> comp() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Comparator<? super Sale> averagePrice() {
		// TODO Auto-generated method stub
		return null;
	}
	}


