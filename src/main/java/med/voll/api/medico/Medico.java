package med.voll.api.medico;



import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medicos")
@Getter //gera métodos gettes
@NoArgsConstructor //gera constrotor vazio
@AllArgsConstructor // gera construtor com todos os atributos
@EqualsAndHashCode(of= "id") //  gera o hashcode encima do id
public class Medico {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded //ficam em classes separadas mais o BD considera como sendo da mesma tabela
	private Endereco endereco;
	private Boolean ativo;

	
	public Medico(DadosCadastroMedico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
	}


	public void atualizarInformações(@Valid DadosAtualizarMedico dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();			
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();			
		}
		if(dados.endereco() != null) {
			this.endereco.atualizarInformacoesEndereco(dados.endereco());			
		}
		
	}


	public void excluir() {
		// TODO Auto-generated method stub
		this.ativo = false;
	}
}
