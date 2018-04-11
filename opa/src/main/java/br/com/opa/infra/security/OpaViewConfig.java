package br.com.opa.infra.security;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@URLMappings(mappings = { 
		@URLMapping(id = "login", pattern = "/login", viewId = "/login.jsf"),
		@URLMapping(id = "index", pattern = "/index", viewId = "/home.jsf"),
		@URLMapping(id = "home", pattern = "/home", viewId = "/home.jsf"),
		@URLMapping(id = "logout", pattern = "/logout", viewId = "/home.jsf"),
		@URLMapping(id = "usuario", pattern = "/usuario", viewId = "/page/usuario/usuario_form.jsf"),
		@URLMapping(id = "oferta", pattern = "/oferta", viewId = "/page/oferta/oferta_form.jsf"),
		
		})
public class OpaViewConfig {

}