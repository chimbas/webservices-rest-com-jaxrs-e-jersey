package br.com.alura.loja.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {

	private HttpServer server;
	private Client client;
	private WebTarget target;

	@Before
	public void startServer() {
		server = Servidor.inicializaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/");
	}
	
	@After
	public void stopServer() {
		server.stop();
	}
	
	@Test
	public void testaTrazOPrimeiroProjeto() {
		Projeto projeto = target.path("projetos/1").request().get(Projeto.class);
        Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testaAdicionarProjeto() {
		Projeto projeto = new Projeto();
		projeto.setNome("Estudar Rest");
		projeto.setAnoDeInicio(2016);

		Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);

		Response response = target.path("/projetos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		
		Projeto projetoCarregado = client.target(location).request().get(Projeto.class);
		Assert.assertEquals("Estudar Rest", projetoCarregado.getNome());
	}
}
