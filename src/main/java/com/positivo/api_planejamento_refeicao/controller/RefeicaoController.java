package com.positivo.api_planejamento_refeicao.controller;


import com.positivo.api_planejamento_refeicao.dto.IngredientesRequestDTO;
import com.positivo.api_planejamento_refeicao.dto.ReceitaResponseDTO;
import com.positivo.api_planejamento_refeicao.dto.RefeicaoResponseDTO;
import com.positivo.api_planejamento_refeicao.dto.TabelaNutricionalResponseDTO;
import com.positivo.api_planejamento_refeicao.service.RefeicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planejamento")
public class RefeicaoController {

    private final RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping("/receitas-disponiveis")
    public ResponseEntity<List<ReceitaResponseDTO>> receitasDisponiveis(@RequestBody IngredientesRequestDTO request){
        return ResponseEntity.ok(refeicaoService.mostrarReceitas(request.ingredientes()));
    }

    @GetMapping("/tabelas-nutricionais")
    public ResponseEntity<List<TabelaNutricionalResponseDTO>> tabelasNutricionais(){
        return ResponseEntity.ok(refeicaoService.mostrarTabelasNutricionais());
    }

    @PostMapping("/receitas-com-tabelas-nutricionais")
    public ResponseEntity<List<RefeicaoResponseDTO>> receitasComTabelasNutricionais(@RequestBody IngredientesRequestDTO request){
        return ResponseEntity.ok(refeicaoService.mostrarReceitasComTabelasNutricionais(request.ingredientes()));
    }




}
