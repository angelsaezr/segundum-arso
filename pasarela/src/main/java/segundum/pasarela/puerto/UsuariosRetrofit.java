package segundum.pasarela.puerto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import segundum.pasarela.puerto.dto.UsuarioGithubInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

public interface UsuariosRetrofit {

	@POST("usuarios/login")
	Call<UsuarioLoginResponseDTO> login(@Body UsuarioLoginInputDTO dto);

	@GET("usuarios/buscarPorGithubId")
	Call<UsuarioLoginResponseDTO> buscarPorGithubId(@Query("githubId") String githubId);

	@POST("usuarios/github")
	Call<Void> crearUsuarioGithub(@Body UsuarioGithubInputDTO dto);
}