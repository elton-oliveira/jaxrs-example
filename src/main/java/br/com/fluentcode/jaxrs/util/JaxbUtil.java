package br.com.fluentcode.jaxrs.util;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil<T> {

	/**
	 * Marshal
	 */
	public String marshal(T object) {
		try {
			Writer writer = new StringWriter();

			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(object, writer);

			return writer.toString();
			
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Unmarshal
	 */
	@SuppressWarnings("unchecked")
	public T unmarshal(String xml, Class<T> classe) {
		try {
			Reader reader = new StringReader(xml);
			
			JAXBContext context = JAXBContext.newInstance(classe);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			return (T) unmarshaller.unmarshal(reader);
			
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

}
