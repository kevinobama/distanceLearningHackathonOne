package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import play.mvc.Http.Session;

import repository.UserRepository;
import models.User;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import helpers.UtilityHelper;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.*;

/**
 * Manage a database of UsersController
 */
public class UsersController extends BaseController {
	
	private final UserRepository userRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;
    
    @Inject
    public UsersController(UserRepository userRepository, FormFactory formFactory,HttpExecutionContext httpExecutionContext) {            	
    	this.userRepository = userRepository;
    	this.formFactory = formFactory;        
        this.httpExecutionContext = httpExecutionContext;
    }
        
    public  Result login() {
    	     	 
        return ok(views.html.users.login.render("login"));
    }    
    
    public Result authenticate() {
    	
    	Form<User> loginForm = formFactory.form(User.class).bindFromRequest();
    	    	    	
    	if (loginForm.hasErrors()) {
    	    return badRequest(views.html.users.login.render("login"));
    	} else {
    	    User user = loginForm.get();

    	    	session("userId", "8888");
    	    	session("email", user.email);
    	    	session("userName", user.email);

    	    	session("role", "student");

				return redirect(routes.DashboardController.index());
    	        	    
//    	    if(this.userRepository.isValid(user.email, user.password)) {
//    	    	User loginedUser = User.find.query().where()
//    					.eq("email", user.email)
//    					.eq("password", UtilityHelper.md5String(user.password))
//    					.findOne();
//
//    	    	session("userId", loginedUser.id.toString());
//    	    	session("email", loginedUser.email);
//    	    	session("userName", loginedUser.name);
//
//    	    	if(loginedUser.role != null && !loginedUser.role.isEmpty()) {
//    	    		session("role", loginedUser.role);
//    	    	} else {
//    	    		session("role", "user");
//    	    	}
//
//
//				return redirect(routes.DashboardController.index());
//    	    } else {
//    	    	return ok("failed login ");
//    	    }
    	}
   }    
        
    /**
     * Logs out (only for authenticated users) and returns them to the Index page. 
     * @return A redirect to the Index page. 
     */
    @Security.Authenticated(Secured.class)
    public Result logout() {
      session().clear();
      return redirect(routes.HomeController.index());
    }   
 

}
