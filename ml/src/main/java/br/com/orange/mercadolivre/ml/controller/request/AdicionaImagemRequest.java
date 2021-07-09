package br.com.orange.mercadolivre.ml.controller.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class AdicionaImagemRequest {

	@NotNull
	@Size(min = 1)
	List<MultipartFile> imagens = new ArrayList<>();

	public AdicionaImagemRequest(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

}
