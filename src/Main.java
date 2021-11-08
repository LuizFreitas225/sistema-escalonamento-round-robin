
import java.util.Random;
import java.util.Scanner;

import model.ListaProcesso;
import model.Processo;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int qtProcessos;
        int quantum;
		
		Scanner leitor = new Scanner(System.in);
		Random gerador = new Random();

		System.out.println("Deseja criar quantos processos?");
		qtProcessos = leitor.nextInt();
		
		System.out.println("Qual ser� o quantum dessa lista de processos?");
		quantum = leitor.nextInt();
        
		ListaProcesso listaProcesso = new ListaProcesso(quantum);
		for (int index = 0; index < qtProcessos; index++) {

			if (index == 0) {
				listaProcesso.getLista().add(new Processo("Processo " + index, 0, gerador.nextInt(25) + 1, null));
			} else {
				listaProcesso.getLista()
						.add(new Processo("Processo " + index, gerador.nextInt(25) + 1, gerador.nextInt(25) + 1, null));
			}

		}

		System.out.println("Os processos forma criados.");
		System.out.println("----------------------------- ");
		System.out.println("INICIANDO A EXECU��O....");
		System.out.println("-----------------------------");

		
			System.out.println("-----------------------------");
			System.out.println("PR� - PROCESSAMENTO: ");
			
   
			listaProcesso.imprimir();
			listaProcesso.executarProcessos();
			
			
			System.out.println("++++++++++++++++++++++++++++++++ ");
			System.out.println("P�S- PROCESSAMENTO: ");
			System.out.println(" ");
			listaProcesso.imprimir();
		

		
		System.out.println("-----------------------------");
		System.out.println("  PROCESSAMENTO CONCLU�DO");
		System.out.println("   -O Tempo m�dio de espera foi de " + listaProcesso.calcularMediaEspera());
		System.out.println("-----------------------------");

		leitor.close();
	}

}
