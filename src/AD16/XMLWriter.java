package AD16;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriter {

	private String ruta = "C:\\Users\\David\\Desktop\\productos.txt";
	private File file = new File(ruta);
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Product[] arr = new Product[3];
	private XMLStreamWriter xsw = null;
	private XMLOutputFactory xof = XMLOutputFactory.newInstance();
	public String fich = "C:\\Users\\David\\Desktop\\products.xml";

	Product po1 = new Product("cod1", "parafusos", 3);
	Product po2 = new Product("cod2", "arandelas", 4);
	Product po3 = new Product("cod3", "tachas", 5);
	Product po4 = null;

	// Método cogido de Serialización2
	public void serializar() {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			oos = new ObjectOutputStream(new FileOutputStream(file));

			oos.writeObject(po1);
			oos.writeObject(po2);
			oos.writeObject(po3);
			oos.writeObject(po4);

			oos.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Método cogido de Serialización2
	private void leerSerializacion() {

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Product x;
			int i = 0;
			while ((x = (Product) ois.readObject()) != null) {
				arr[i] = x;
				i++;
			}
			ois.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para crear cada objeto Product como xml
	private void XML(Product[] s) throws XMLStreamException, IOException {
		xsw = xof.createXMLStreamWriter(new FileWriter(fich));
		xsw.writeStartDocument("1.0");
		xsw.writeStartElement("produtos"); // Inicio etiqueta raíz.

		int i = 0;
		while (i < s.length) {
			xsw.writeStartElement("produto");
			xsw.writeAttribute("codigo", s[i].getCodigo());

			xsw.writeStartElement("desc");
			xsw.writeCharacters(s[i].getDescripcion());
			xsw.writeEndElement();

			xsw.writeStartElement("prezo");
			xsw.writeCharacters(String.valueOf(s[i].getPrecio()));
			xsw.writeEndElement();

			xsw.writeEndElement();

			i++;
		}

		xsw.writeEndElement(); // Fin tiqueta raíz.
		xsw.close(); // Cerramos el Streaming al fichero.

	}

	// Método principal, donde serializamos, leermos y creamos el xml.
	public void crearXMLObjetos() throws XMLStreamException, IOException {
		serializar();
		leerSerializacion(); // Método propio para leer objetos serializados.
		XML(arr); // Método propio para etiquetar en XML cada objeto.

		Desktop d = Desktop.getDesktop(); // Abrir el fichero xml en el navegador.
		d.open(new File(fich));

	}

	public static void main(String[] args) {
		XMLWriter aux = new XMLWriter();
		try {
			aux.crearXMLObjetos();

		} catch (XMLStreamException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
