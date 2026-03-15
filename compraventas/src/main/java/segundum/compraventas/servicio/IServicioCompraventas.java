package segundum.compraventas.servicio;

import segundum.compraventas.modelo.Compraventa;

public interface IServicioCompraventas {
	Compraventa crearCompraventa(String titulo, double precio);
}