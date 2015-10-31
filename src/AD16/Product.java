package AD16;

import java.io.Serializable;

public class Product implements Serializable{
	private String codigo;
	private String descripcion;
	private int precio;

	public Product() {
	}

	public Product(String codigo, String descripcion, int precio) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
