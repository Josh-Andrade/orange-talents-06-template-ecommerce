package br.com.orange.mercadolivre.ml.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	// Simula o envio das imagens para um ambient cloud e retona lista de strings
	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream().map(file -> "http://bucket.io" + file.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
