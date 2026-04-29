package segundum.productos.rest.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import segundum.productos.modelo.Categoria;

@Schema(description = "DTO de respuesta con los datos de una categoría")
public class CategoriaDTO {

	@Schema(description = "Identificador único de la categoría")
	private String id;
	@Schema(description = "Nombre de la categoría")
	private String nombre;
	@Schema(description = "Descripción de la categoría")
	private String descripcion;
	@Schema(description = "Ruta jerárquica de la categoría")
	private String ruta;

	@JsonInclude(Include.NON_EMPTY)
	@Schema(description = "Lista de subcategorías de primer nivel")
	private List<EntityModel<CategoriaDTO>> subcategorias = new LinkedList<>();

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public List<EntityModel<CategoriaDTO>> getSubcategorias() {
		return subcategorias;
	}

	public String toString() {
		return "CategoriaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ruta=" + ruta
				+ ", subcategorias=" + subcategorias.size() + "]";
	}

	public static CategoriaDTO fromEntity(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.id = categoria.getId();
		dto.nombre = categoria.getNombre();
		dto.descripcion = categoria.getDescripcion();
		dto.ruta = categoria.getRuta();
		// Convertir solo la primera generación de subcategorías
		if (categoria.getSubcategorias() != null) {
			for (Categoria subcategoria : categoria.getSubcategorias()) {
				CategoriaDTO subDto = fromEntitySinSubcategorias(subcategoria);
				// Crear un EntityModel para cada subcategoría sin links (se agregarán en el
				// Assembler)
				dto.subcategorias.add(EntityModel.of(subDto));
			}
		}
		return dto;
	}

	public static CategoriaDTO fromEntitySinSubcategorias(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.id = categoria.getId();
		dto.nombre = categoria.getNombre();
		dto.descripcion = categoria.getDescripcion();
		dto.ruta = categoria.getRuta();
		// No incluir subcategorías (lista vacía)
		return dto;
	}

	public static CategoriaDTO fromEntityParaDescendientes(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.id = categoria.getId();
		dto.nombre = categoria.getNombre();
		dto.descripcion = categoria.getDescripcion();
		dto.ruta = categoria.getRuta();
		// No incluir subcategorías en contexto de descendientes
		// (cada descendiente se consulta por separado)
		return dto;
	}
}
