package com.positivo.api_planejamento_refeicao.service;

import com.positivo.api_planejamento_refeicao.client.ApiReceitaClient;
import com.positivo.api_planejamento_refeicao.client.ApiTabelaNutricionalClient;
import com.positivo.api_planejamento_refeicao.dto.ReceitaResponseDTO;
import com.positivo.api_planejamento_refeicao.dto.RefeicaoResponseDTO;
import com.positivo.api_planejamento_refeicao.dto.TabelaNutricionalResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefeicaoService {

    private final ApiReceitaClient apiReceitaClient;
    private final ApiTabelaNutricionalClient apiTabelaNutricionalClient;

    public RefeicaoService(ApiReceitaClient apiReceitaClient, ApiTabelaNutricionalClient apiTabelaNutricionalClient) {
        this.apiReceitaClient = apiReceitaClient;
        this.apiTabelaNutricionalClient = apiTabelaNutricionalClient;
    }

    @Cacheable(value = "receitas-por-ingredientes", key = "#ingredientes")
    public List<ReceitaResponseDTO> mostrarReceitas (List<String> ingredientes) {
        return apiReceitaClient.buscarReceitasPorIngrediente(ingredientes);
    }

    @Cacheable(value = "tabela-nutricional-todas")
    public List<TabelaNutricionalResponseDTO> mostrarTabelasNutricionais(){
        return apiTabelaNutricionalClient.buscarTodasTabelasNutricionais();
    }

    @Cacheable(value = "receitas-com-tabelas", key = "#ingredientes")
    public List<RefeicaoResponseDTO> mostrarReceitasComTabelasNutricionais(List<String> ingredientes) {

        List<ReceitaResponseDTO> receitas = mostrarReceitas(ingredientes);
        List<RefeicaoResponseDTO> refeicoes = new ArrayList<>();

        for (ReceitaResponseDTO receita : receitas) {

            float caloriasTotais = 0.0f;
            float proteinasTotais = 0.0f;
            float carboidratosTotais = 0.0f;
            float gordurasTotais = 0.0f;

            List<TabelaNutricionalResponseDTO> tabelas = apiTabelaNutricionalClient.buscarTabelasNutricionais(receita.ingredientes());

            for (TabelaNutricionalResponseDTO tabela : tabelas) {
                caloriasTotais += tabela.calorias();
                proteinasTotais += tabela.proteinas();
                carboidratosTotais += tabela.carboidratos();
                gordurasTotais += tabela.gorduras();
            }

            RefeicaoResponseDTO refeicao = new RefeicaoResponseDTO(
                    receita.nome(),
                    receita.descricao(),
                    receita.ingredientes(),
                    caloriasTotais,
                    proteinasTotais,
                    carboidratosTotais,
                    gordurasTotais
            );

            refeicoes.add(refeicao);

        }

        return refeicoes;

    }

}
