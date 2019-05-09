package it.lt.arminai.moneyTransfer.controller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

@RunWith(Arquillian.class)
public class UserControllerIT {
    private final static String WARNAME = "money-transfer.war";

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "lt.arminai.moneyTransfer");
        return archive;
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    @InSequence(1)
    public void testInventoryEndpoints() throws Exception {
        String localhosturl = baseURL + "/localhost";
    }
}
