package com.positivo.api_planejamento_refeicao.dto;

import java.util.List;

public record ReceitaResponseDTO(
        String nome,
        String descricao,
        List<String> ingredientes
) {
}
