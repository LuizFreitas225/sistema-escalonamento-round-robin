package model;

import java.util.ArrayList;
import java.util.List;

public class ListaProcesso {
	Integer quantum;
	List<Processo> lista = new ArrayList<Processo>();

	public ListaProcesso(Integer quantum) {

		this.quantum = quantum;

	}

	// Executa = Atualiza o burtTime e o tempo de espera dd todos os processos
	public void executarProcessos() {
		Integer tempoProcessamento = 0;
		for (int index = 0; index < getLista().size(); index++) {
			// IMPEDE DE EXECUTAR UM PROCESSO QUE JÁ FOI CONCLUIDO
			if (getLista().get(index).getBt() == 0) {
				continue;
			}
			tempoProcessamento = executar(index);
			for (int i = 0; i < getLista().size(); i++) {
				// IMPEDE DE ATUALIZAR O TEMPO DE ESPERA DO PROCESSO EXECUTADO
				if (i == index)
					continue;

				// IMPEDE DE ATUALIZAR O TEMPO DE ESPERA DE UM PROCESSO QUE JÁ FOI CONCLUIDO
				else if (getLista().get(i).getBt() == 0) {
					continue;
				} else {
					// É ADICIONADO AO TEMPO DE ESPERA O TEMPO DE EXECUÇÃO DO ULTIMO PROCESSO
					getLista().get(i).setTempoEspera(getLista().get(i).getTempoEspera() + tempoProcessamento);
				}
			}
		}
        //Repete a execução caso tenha algum processo não concluído
		for (int index = 0; index < getLista().size(); index++) {
			if (getLista().get(index).getBt() > 0) {
				executarProcessos();
			}
		}
	}

	// Retorna o tempo de execução do processo
	// Se retornar 0 é pq não ouve execução
	// O tempo limite de execução é o quanum da lista
	private Integer executar(int index) {
		if (getLista().get(index).getBt() >= getQuantum()) {
			// BURT TIME DO PROCESSO RECEBE ELE MESMO MENOS O QUANTUM
			getLista().get(index).setBt(getLista().get(index).getBt() - getQuantum());
			return getQuantum();
		}
		if (getLista().get(index).getBt() == 0) {
			return 0;
		} else {
			int burtsAux = getLista().get(index).getBt();
			getLista().get(index).setBt(0);
			return burtsAux;
		}
	}

	public void imprimir() {
		for (int index = 0; index < getLista().size(); index++) {
			getLista().get(index).imprimir();
		}

	}

	public Double calcularMediaEspera() {
		Double somatoriaEspera = 0.0;
		for (int index = 0; index < getLista().size(); index++) {
			somatoriaEspera += getLista().get(index).getTempoEspera();
		}
		return (Double) (somatoriaEspera / getLista().size());
	}

	public Integer getQuantum() {
		return quantum;
	}

	public void setQuantum(Integer quantum) {
		this.quantum = quantum;
	}

	public List<Processo> getLista() {
		if (lista == null) {
			lista = new ArrayList<Processo>();
		}
		return lista;
	}

	public void setLista(List<Processo> lista) {
		this.lista = lista;
	}

}
