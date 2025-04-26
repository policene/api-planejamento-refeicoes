package com.positivo.api_planejamento_refeicao.dto;

public record TabelaNutricionalResponseDTO(
        String nome,
        Float calorias,
        Float proteinas,
        Float carboidratos,
        Float gorduras,
        String quantidade_base
) {
}
