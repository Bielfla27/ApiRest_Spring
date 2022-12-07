package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.GeneratedValue;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	@Transactional // para ter uma transação ativa com o banco
	public void  cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		medicoRepository.save(new Medico(dados));
	}
	
	@GetMapping
	public List<DadosListagemMedico> listar(){
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList(); //chamando método listar todos jpa e convertendo o dadosListagemMedico para um tipo médico
	}
}
