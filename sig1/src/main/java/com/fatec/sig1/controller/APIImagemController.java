package com.fatec.sig1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.sig1.model.Imagem;
import com.fatec.sig1.services.MantemImagem;

@RestController
@RequestMapping("/imagens")
public class APIImagemController {

    private final MantemImagem mantemImagem;

    public APIImagemController(MantemImagem mantemImagem) {
        this.mantemImagem = mantemImagem;
    }

    @PostMapping
    public ResponseEntity<Imagem> uploadImagem(@RequestParam("imagem") MultipartFile arquivo) throws IOException {
        Imagem imagem = mantemImagem.salvar(arquivo);
        return ResponseEntity.ok(imagem);
    }

    @GetMapping
    public List<Imagem> listarImagens() {
        return mantemImagem.listar();
    }
}
