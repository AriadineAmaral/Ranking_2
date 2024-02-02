package lab;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Sprint04 {

	static Scanner e = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random random = new Random();
		DecimalFormat nota = new DecimalFormat("0");

		int numEquipes = 0;
		int numCombate = 0;
		int auxVitorias = 0;
		String auxEquipe = "";

		System.out.println("Digite a quantidade de equipes que irão participar da competição");
		numEquipes = e.nextInt();

		// VALIDAÇÃO Nº DE EQUIPES

		while (numEquipes < 11 || numEquipes > 99) {
			System.out.println(
					"O número de equipes inserido não está de acordo com as regras da competição.\nÉ necessário que tenham entre (11 e 99) equipes participantes para iniciarmos as competições.");
			numEquipes = e.nextInt();
		}

		// DECLARAÇÃO VETORES E MATRIZ
		double designNotas[] = new double[numEquipes];
		String[] equipes = new String[numEquipes];
		double resultado[][] = new double[numEquipes][(numEquipes)];
		int totVitorias[] = new int[numEquipes];
		int totVitoriasF[] = new int[3];

		for (int i = 0; i < numEquipes; i++) {
			System.out.println("Digite o nome da " + (i + 1) + "ª equipe ");
			equipes[i] = e.next().toUpperCase();
			System.out.println("Digite a nota de design para a equipe: " + equipes[i]);
			designNotas[i] = e.nextInt();

			while (designNotas[i] >10 || designNotas[i] <0 ) {
				System.out.println("Nota inválida!\nDigite uma nota entre 0 e 10");
				designNotas[i] = e.nextInt();
				
			}
			
		}
		
		
		

		System.out.println("\nChaves de competição:");
		for (int i = 0; i < numEquipes - 1; i++) {
			for (int j = i + 1; j < numEquipes; j++) {
				System.out.println(equipes[i] + " vs " + equipes[j]);

			}
		}

		System.out.println("\nResultados dos embates:");

		for (int i = 0; i < numEquipes - 1; i++) {
			for (int j = i + 1; j < numEquipes; j++) {
				resultado[i][j] = random.nextInt(10);
				resultado[j][i] = random.nextInt(10);
				System.out.println(equipes[i] + " vs " + equipes[j] + " = "
						+ (nota.format(resultado[i][j]) + "vs" + (nota.format(resultado[j][i]))));

			}
		}
		System.out.println("");
		double vencedor[][] = vencedorEmbates(resultado, numEquipes, equipes, numCombate, designNotas, totVitorias);

		// ORDENANDO AS VITORIAS EM ORDEM DECRESCENTE

		for (int i = 0; i < numEquipes - 1; i++) {
			for (int j = 0; j < numEquipes - i - 1; j++) {
				if (totVitorias[j] < totVitorias[j + 1]) {

					auxVitorias = totVitorias[j];
					totVitorias[j] = totVitorias[j + 1];
					totVitorias[j + 1] = auxVitorias;

					auxEquipe = equipes[j];
					equipes[j] = equipes[j + 1];
					equipes[j + 1] = auxEquipe;
				}
			}
		}

		System.out.println("");
		vitorias(numEquipes, equipes, totVitorias);

		// FINAL
		System.out.println("\nChaves da grande final:");
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				System.out.println(equipes[i] + " vs " + equipes[j]);

			}
		}

		System.out.println("\nResultados dos embates:");

		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				resultado[i][j] = random.nextInt(10);
				resultado[j][i] = random.nextInt(10);
				System.out.println(equipes[i] + " vs " + equipes[j] + " = "
						+ (nota.format(resultado[i][j]) + "vs" + (nota.format(resultado[j][i]))));

			}
		}

		System.out.println();
		double vencedorFinal[][] = vencedorFinal(resultado, equipes, numCombate, designNotas, totVitoriasF);

		System.out.println("");
		vencedor(equipes, totVitoriasF, numCombate, designNotas);

	}// main

	public static double[][] vencedorEmbates(double[][] resultado, int numEquipes, String[] equipes, int numCombate,
			double[] designNotas, int[] totVitorias) {

		double vencedor[][] = new double[numEquipes][numEquipes];

		for (int i = 0; i < numEquipes - 1; i++) {
			for (int j = i + 1; j < numEquipes; j++) {
				numCombate++;
				if (resultado[i][j] < resultado[j][i]) {
					vencedor[i][j] = resultado[j][i];
					System.out.println("Vencedor do " + numCombate + "º combate - equipe " + equipes[j]);
					totVitorias[j]++;
				} else {
					if (resultado[i][j] > resultado[j][i]) {
						vencedor[i][j] = resultado[i][j];
						System.out.println("Vencedor do " + numCombate + "º combate - equipe " + equipes[i]);
						totVitorias[i]++;
					} else {
						System.out.println("Houve um empate entre as equipes: " + equipes[i] + " e " + equipes[j]
								+ " no " + numCombate + "º embate");

						if (designNotas[i] > designNotas[j]) {
							System.out.println("A nota de desing da equipe: " + equipes[i]
									+ " é maior, portanto, a mesma será a vencedora do combate");
							totVitorias[i]++;
						} else {
							System.out.println("A nota de desing da equipe: " + equipes[j]
									+ " é maior, portanto, a mesma será a vencedora do combate");
							totVitorias[j]++;
						}

					}

				}
			}

		}
		return vencedor;
	}

	public static void vitorias(int numEquipes, String[] equipes, int[] totVitorias) {

		for (int i = 0; i < numEquipes; i++) {
			System.out.println(equipes[i] + ": " + totVitorias[i] + " vitórias");

		}
		System.out.println("");
		System.out.println("As três melhores equipes irão desputar a grande final!");
		for (int i = 0; i < 3; i++) {

			System.out.println(equipes[i] + " - total de vitórias: " + totVitorias[i]);

		}

	}

	public static double[][] vencedorFinal(double[][] resultado, String[] equipes, int numCombate, double[] designNotas,
			int[] totVitoriasF) {

		double vencedorFinal[][] = new double[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				numCombate++;
				if (resultado[i][j] < resultado[j][i]) {
					vencedorFinal[i][j] = resultado[j][i];
					System.out.println("Vencedor do " + numCombate + "º combate - equipe " + equipes[j]);
					totVitoriasF[j] += 2;

				} else {
					if (resultado[i][j] > resultado[j][i]) {
						vencedorFinal[i][j] = resultado[i][j];
						System.out.println("Vencedor do " + numCombate + "º combate - equipe " + equipes[i]);
						totVitoriasF[i] +=  2;
					} else {
						System.out.println("Houve um empate entre as equipes: " + equipes[i] + " e " + equipes[j]
								+ " no " + numCombate + "º embate");

						if (designNotas[i] > designNotas[j]) {
							System.out.println("A nota de desing da equipe: " + equipes[i]
									+ " é maior, portanto, a mesma será a vencedora do combate");
							totVitoriasF[i] += totVitoriasF[i] + 1;
						} else {
							System.out.println("A nota de desing da equipe: " + equipes[j]
									+ "é maior, portanto, a mesma será a vencedora do combate");
							totVitoriasF[j] += totVitoriasF[j] + 1;
						}

					}

				}
			}
		}

		return vencedorFinal;
	}

	public static void vencedor( String[] equipes, int[] totVitoriasF, int numCombate, double[] designNotas) {
		int auxVFinal = 0;
		String auxEFinal = "";

		
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				numCombate++;
				if (totVitoriasF[i] == totVitoriasF[j]) {
		          System.out.println("Houve um empate no total de vitórias do Ranking Final, a maior nota de desing irá definir o ganhador");  

		            if (designNotas[i] > designNotas[j]) {
		                System.out.println("A nota de design da equipe: " + equipes[i]
		                        + "  é maior, portanto a mesma ficará uma posição acima");
		                totVitoriasF[i]++;
		            } else if (designNotas[i] < designNotas[j]) {
		                System.out.println("A nota de design da equipe: " + equipes[j]
		                        + "  é maior, portanto a mesma ficará uma posição acima");
		                totVitoriasF[j]++;
		            
		        }
		    }
		}	
	}
		
		for (int i = 0; i < 3 - 1; i++) {
		    for (int j = 0; j < 3 - i - 1; j++) {
		        if (totVitoriasF[j] < totVitoriasF[j + 1]) {
		            auxVFinal = totVitoriasF[j];
		            totVitoriasF[j] = totVitoriasF[j + 1];
		            totVitoriasF[j + 1] = auxVFinal;

		            auxEFinal = equipes[j];
		            equipes[j] = equipes[j + 1];
		            equipes[j + 1] = auxEFinal;
		        }
		    }
		}
		
		System.out.println("");
		for (int i = 0; i < 3; i++) {
			System.out.println(equipes[i] + ": " + totVitoriasF[i] + " vitórias");

		}
		
		System.out.println("\nRanking");
		for (int i = 0; i < 3; i++) {
			System.out.println((i+1)+ "º Lugar - Equipe: "+equipes[i] + " - total de vitórias: " + totVitoriasF[i]);
			
		}
		
		System.out.println("");
		System.out.println("A equipe vencedora foi: " + equipes[0]);
	}

}
