package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizarMedico;
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
	public Page<DadosListagemMedico> listar(@PageableDefault(size =10, sort = {"nome"}) Pageable paginacao){
		return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); //chamando método listar todos jpa e convertendo o dadosListagemMedico para um tipo médico
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformações(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();
	}
}
