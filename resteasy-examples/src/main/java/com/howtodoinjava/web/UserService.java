package com.howtodoinjava.web;

import com.howtodoinjava.model.User;
import com.howtodoinjava.model.Users;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user-management")
@Path("/user-management")
public class UserService {

  @GET
  @Path("/")
  @Produces("application/xml")
  public UserService getServiceInfo() {
    return new UserService();
  }

  @GET
  @Path("/users")
  @Produces("application/xml")
  public Users getAllUsers() {
    Users users = new Users();
    users.setUsers(UserCache.getUsers());
    return users;
  }

  @GET
  @Path("/users/{id}")
  @Produces("application/xml")
  public User getUserById(@PathParam("id") int id) {
    if (id > UserCache.getUsers().size()) {
      //error
    }
    return UserCache.getUsers().get(id - 1);
  }

  @POST
  @Path("/users")
  @Consumes("application/xml")
  public Response createUser(User user)
      throws URISyntaxException {
    UserCache.addUser(user);
    return Response.status(201)
        .contentLocation(new URI("/user-management/users/" + user.getId())).build();
  }

  @PUT
  // @Path("/users/{id: [0-9]*}")
  @Path("/users/{id}")
  @Consumes("application/xml")
  @Produces("application/xml")
  public User updateUser(@PathParam("id") int id, User user)
      throws URISyntaxException {
    user.setId(id);
    user.setFirstName(user.getFirstName() + "updated");
    return user;
  }

  @DELETE
  @Path("/users/{id}")
  public Response deleteUser(@PathParam("id") int id)
      throws URISyntaxException {
    UserCache.getUsers().remove(id - 1);
    return Response.status(200).build();
  }
}

class UserCache {

  private static List<User> users = new ArrayList();

  public static List<User> getUsers() {
    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Alex");
    user1.setLastName("Cook");

    User user2 = new User();
    user2.setId(2);
    user2.setFirstName("Brian");
    user2.setLastName("Lara");

    users.add(user1);
    users.add(user2);
    return users;
  }

  public static void addUser(User user) {
    users.add(user);
  }

  public static void updateUser(User user, int id) {
    users.set(id - 1, user);
  }
}
