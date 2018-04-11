
package br.com.opa.infra.security;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

	public void onInit(@Observes SecurityConfigurationEvent event) {

		SecurityConfigurationBuilder builder = event.getBuilder();

		builder.http()/*.allPaths()
    	.authenticateWith().form().authenticationUri("/login").loginPage("/login").errorPage("/login").restoreOriginalRequest()*/
			.forPath("/javax.faces.resource/*").unprotected()
			.forPath("/resources/*").unprotected()
			.forPath("/recuperarsenha/*").unprotected()
			.forPath("/home/*").unprotected()
			.forPath("/home").unprotected()
			.forPath("/opa/*").unprotected()
			.forPath("/opa").unprotected()
			.forPath("/page/*").unprotected()
			.forPath("/page/").unprotected()
		    .forPath("/logout").logout().redirectTo("/home");
	}

}