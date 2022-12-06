package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable //ficam em classes separadas mais o BD considera como sendo da mesma tabela

@Getter //gera métodos gettes
@NoArgsConstructor //gera constrotor vazio
@AllArgsConstructor // gera construtor com todos os atributos
public class Endereco {
	
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String complemento;
	private String numero;

}
