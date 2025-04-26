package com.positivo.api_planejamento_refeicao.dto;

import java.util.List;

public record RefeicaoResponseDTO(
        String nome,
        String descricao,
        List<String> ingredientes,
        Float caloriasTotais,
        Float proteinasTotais,
        Float carboidratosTotais,
        Float gordurasTotais
) {
}
