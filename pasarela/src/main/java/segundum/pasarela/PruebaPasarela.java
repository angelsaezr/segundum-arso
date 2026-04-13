package segundum.pasarela;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import segundum.pasarela.adaptador.ClienteUsuariosRetrofit;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

public class PruebaPasarela {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext contexto = SpringApplication.run(PasarelaApplication.class, args);

		ClienteUsuariosRetrofit clienteUsuarios = contexto.getBean(ClienteUsuariosRetrofit.class);
		UsuarioLoginInputDTO usuarioInputDto = new UsuarioLoginInputDTO();
		usuarioInputDto.setEmail("juan2@um.es");
		usuarioInputDto.setPassword("1234");
		UsuarioLoginResponseDTO usuarioLoginResponseDto = clienteUsuarios.login(usuarioInputDto);
		System.out.println("Id: " + usuarioLoginResponseDto.getId());
		System.out.println("Email: " + usuarioLoginResponseDto.getEmail());
		System.out.println("Nombre: " + usuarioLoginResponseDto.getNombre());
		System.out.println("Apellidos: " + usuarioLoginResponseDto.getApellidos());
		System.out.println("Roles: " + usuarioLoginResponseDto.getRoles());
	}

}
