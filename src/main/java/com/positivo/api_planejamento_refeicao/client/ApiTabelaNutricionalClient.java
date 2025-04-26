package com.positivo.api_planejamento_refeicao.client;

import com.positivo.api_planejamento_refeicao.dto.IngredientesRequestDTO;
import com.positivo.api_planejamento_refeicao.dto.TabelaNutricionalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiTabelaNutricionalClient {

    private final RestTemplate restTemplate;

    public ApiTabelaNutricionalClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public List<TabelaNutricionalResponseDTO> buscarTodasTabelasNutricionais() {
        String url = "http://tb-nutricional-api:8000/ingredientes";

        ResponseEntity<TabelaNutricionalResponseDTO[]> response = restTemplate.getForEntity(
                url,
                TabelaNutricionalResponseDTO[].class
        );

        return Arrays.asList(response.getBody());
    }



    public List<TabelaNutricionalResponseDTO> buscarTabelasNutricionais(List<String> ingredientes) {
        String url = "http://tb-nutricional-api:8000/ingredientes/buscar-por-lista";

        IngredientesRequestDTO request = new IngredientesRequestDTO(ingredientes);

        ResponseEntity<TabelaNutricionalResponseDTO[]> response = restTemplate.postForEntity(
                url,
                request,
                TabelaNutricionalResponseDTO[].class
        );

        return Arrays.asList(response.getBody());
    }




}
