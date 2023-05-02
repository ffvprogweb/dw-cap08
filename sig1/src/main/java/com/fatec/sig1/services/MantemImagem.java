package com.fatec.sig1.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.sig1.model.Imagem;
import com.fatec.sig1.model.ImagemRepository;

@Service
public class MantemImagem {

    private ImagemRepository imagemRepository = null;

    private Environment environment = null;

    public MantemImagem(ImagemRepository imagemRepository, Environment environment) {
        this.imagemRepository = imagemRepository;
        this.environment = environment;
    }

    public Imagem salvar(MultipartFile arquivo) throws IOException {
        String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
        String caminhoArquivo = environment.getProperty("imagem.upload-dir") + "/" + nomeArquivo;
        Path caminhoCompleto = Paths.get(caminhoArquivo).toAbsolutePath().normalize();
        Files.copy(arquivo.getInputStream(), caminhoCompleto, StandardCopyOption.REPLACE_EXISTING);
        Imagem imagem = new Imagem();
        imagem.setNome(nomeArquivo);
        imagem.setCaminho(caminhoCompleto.toString());
        return imagemRepository.save(imagem);
    }

    public List<Imagem> listar() {
        return imagemRepository.findAll();
    }
}
