package nl.atos.devlab.microlearning.googleservice.rest;

import java.net.URL;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test if the service is up and running.
 * Because of the restrictions of usage by Google, the REST services for accessing the Google API are not tested.
 */
@RunWith(Arquillian.class)
public class GoogleSearchResourceTest {

    @ArquillianResource
    private URL url;

    private WebTarget target;

    @Before
    public void initialize() throws Exception {
        target = ClientBuilder.newClient().target(url.toURI());
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "google-search-test.war")
            .addPackage("nl.atos.devlab.microlearning.googleservice")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @RunAsClient
    public void testIfServerIsUpAndRunning() {
        Assert.assertEquals(200, target.path("health").request().get().getStatus());
    }
}
