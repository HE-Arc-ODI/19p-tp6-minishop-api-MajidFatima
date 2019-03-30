/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.exception.NotFoundException;
import ch.hearc.ig.odi.minishop.exception.NullFormException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CustomerResource {

  @Inject
  private PersistenceService persistenceService;
/*
Pour la ressource GET /customer, il faut que la ressources
retourne un tableaux d'objets JSON (MediaType: application/json), voici un exemple :
*/
  @GET
  public List<Customer> getCustomers() {
    return persistenceService.getAllCustomers();
  }

  /*
  Pour la ressource GET /customer/1, il faut que la ressources retourne
   un objet JSON (MediaType: application/json), voici un exemple :
   */

  @GET
  @Path("{id}")
  public Customer getCustomer(@Context HttpServletRequest servletRequest, @PathParam("id") Long id) {
    try {
      return persistenceService.getCustomerByID(id);
    } catch (CustomerException e) {
      e.printStackTrace();
      throw new NotFoundException("the customer does not exist.");
    }
  }
}

