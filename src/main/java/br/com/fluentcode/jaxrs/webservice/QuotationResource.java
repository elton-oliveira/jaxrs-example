package br.com.fluentcode.jaxrs.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/quotation")
public class QuotationResource {

	private final static String COTACAO_DOLLAR = "2.2";
	private final static String COTACAO_EURO = "3.1";
	private final static String DEFAULT = "Moeda não suportada";

	/**
	 * Simple example.
	 * 
	 * Url: /resources/quotation/dollarToReal
	 * 
	 */
	@GET
	@Path("/dollarToReal")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuotationDollarToReal() {
		return COTACAO_DOLLAR;
	}
	
	/**
	 * Simple example.
	 * 
	 * Url: /resources/quotation/euroToReal
	 * 
	 */
	@GET
	@Path("/euroToReal")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuotationEuroToReal() {
		return COTACAO_EURO;
	}

	/**
	 * Path param example.
	 * 
	 * Url pattern: /resources/quotation/currency/dollar
	 * 
	 */
	@GET
	@Path("/currency/{currencyName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuotationToReal(@PathParam("currencyName") final String currency) {
		String cotacao = DEFAULT;

		if ("dollar".equalsIgnoreCase(currency)) {
			cotacao = getQuotationDollarToReal();
		} else if ("euro".equalsIgnoreCase(currency)) {
			cotacao = getQuotationEuroToReal();
		}

		return cotacao;
	}

	/**
	 * Query param example.
	 * 
	 * Url pattern: /resources/quotation?currency=dollar
	 * 
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuotationToReal_(@QueryParam("currency") final String currency) {
		return getQuotationToReal(currency);
	}

}
