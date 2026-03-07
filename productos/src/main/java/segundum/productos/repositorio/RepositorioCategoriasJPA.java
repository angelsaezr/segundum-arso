package segundum.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import segundum.productos.modelo.Categoria;

public interface RepositorioCategoriasJPA extends RepositorioCategorias, JpaRepository<Categoria, String> {

}