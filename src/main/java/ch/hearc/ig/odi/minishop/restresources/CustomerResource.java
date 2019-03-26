/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.exception.NullFormException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CustomerResource {

  @Inject
  private PersistenceService persistenceService;

  @GET
  public List<Customer> getCustomers() {
    return persistenceService.getAllCustomers();
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Customer updateCustomer(@PathParam("id") Long id, Customer customer) {
    try {
      return persistenceService.updateCustomer(id, customer);
    } catch (CustomerException e) {
      e.printStackTrace();
      throw new NullFormException("customer couldn't have been updated.");
    }
  }
}

