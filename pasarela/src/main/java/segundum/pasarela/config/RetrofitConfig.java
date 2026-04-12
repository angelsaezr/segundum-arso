package segundum.pasarela.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import segundum.pasarela.puerto.UsuariosRetrofit;

@Configuration
public class RetrofitConfig {

	@Value("${microservicio.usuarios.url}")
	private String urlUsuarios;

	@Bean
	public UsuariosRetrofit retrofitUsuariosApi() {
		return new Retrofit.Builder().baseUrl(urlUsuarios).addConverterFactory(GsonConverterFactory.create()).build()
				.create(UsuariosRetrofit.class);
	}
}
