package com.positivo.api_planejamento_refeicao.client;

import com.positivo.api_planejamento_refeicao.dto.IngredientesRequestDTO;
import com.positivo.api_planejamento_refeicao.dto.ReceitaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ApiReceitaClient {

    private final RestTemplate restTemplate;

    public ApiReceitaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ReceitaResponseDTO> buscarReceitasPorIngrediente(List <String> ingredientes) {
        String url = "http://receitas-api:8080/receitas/buscar-por-ingredientes";

        IngredientesRequestDTO request = new IngredientesRequestDTO(ingredientes);

        ResponseEntity<ReceitaResponseDTO[]> response = restTemplate.postForEntity(
                url,
                request,
                ReceitaResponseDTO[].class
        );

        return Arrays.asList(response.getBody());
    }
}
