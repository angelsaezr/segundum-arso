package segundum.compraventas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import segundum.compraventas.puerto.ProductosRetrofit;
import segundum.compraventas.puerto.UsuariosRetrofit;

@Configuration
public class RetrofitConfig {

	@Value("${microservicio.productos.url}")
	private String urlProductos;

	@Value("${microservicio.usuarios.url}")
	private String urlUsuarios;

	@Bean
	public ProductosRetrofit retrofitProductosApi() {
		return new Retrofit.Builder().baseUrl(urlProductos).addConverterFactory(GsonConverterFactory.create()).build()
				.create(ProductosRetrofit.class);
	}

	@Bean
	public UsuariosRetrofit retrofitUsuariosApi() {
		return new Retrofit.Builder().baseUrl(urlUsuarios).addConverterFactory(GsonConverterFactory.create()).build()
				.create(UsuariosRetrofit.class);
	}
}
