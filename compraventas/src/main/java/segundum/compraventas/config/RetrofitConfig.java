package segundum.compraventas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import segundum.compraventas.retrofit.ProductosAPI;
import segundum.compraventas.retrofit.UsuariosAPI;

@Configuration
public class RetrofitConfig {

	@Value("${microservicio.productos.url}")
	private String urlProductos;

	@Value("${microservicio.usuarios.url}")
	private String urlUsuarios;

	@Bean
	public ProductosAPI retrofitProductosApi() {
		return new Retrofit.Builder().baseUrl(urlProductos).addConverterFactory(GsonConverterFactory.create()).build()
				.create(ProductosAPI.class);
	}

	@Bean
	public UsuariosAPI retrofitUsuariosApi() {
		return new Retrofit.Builder().baseUrl(urlUsuarios).addConverterFactory(GsonConverterFactory.create()).build()
				.create(UsuariosAPI.class);
	}
}
