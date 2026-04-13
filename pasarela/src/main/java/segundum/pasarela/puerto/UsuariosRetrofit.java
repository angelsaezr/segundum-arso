package segundum.pasarela.puerto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

public interface UsuariosRetrofit {

	@POST("usuarios/login")
	Call<UsuarioLoginResponseDTO> login(@Body UsuarioLoginInputDTO dto);

	@GET("usuarios/buscarPorEmail")
	Call<UsuarioLoginResponseDTO> buscarPorEmail(@Query("email") String email);
}