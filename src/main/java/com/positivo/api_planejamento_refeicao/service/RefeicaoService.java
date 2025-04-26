package com.positivo.api_planejamento_refeicao.service;

import com.positivo.api_planejamento_refeicao.client.ApiReceitaClient;
import com.positivo.api_planejamento_refeicao.dto.ReceitaResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    private final ApiReceitaClient apiReceitaClient;

    public RefeicaoService(ApiReceitaClient apiReceitaClient) {
        this.apiReceitaClient = apiReceitaClient;
    }

    @Cacheable(value = "refeicoes_por_id", key = "#ingredientes")
    public List<ReceitaResponseDTO> mostrarRefeicoes (List<String> ingredientes) {
        return apiReceitaClient.buscarReceitasPorIngrediente(ingredientes);
    }
}
