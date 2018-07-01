package controllers;

//import play.api.Logger;
//import java.sql.*;  
//import play.db.*;

import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Manage a database of home
 */
public class TeachersController extends BaseController {
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public TeachersController(FormFactory formFactory,
                              HttpExecutionContext httpExecutionContext) {
        this.formFactory = formFactory;         
        this.httpExecutionContext = httpExecutionContext;
    }

//    /**
//     * This result directly redirect to application home.
//     */
//    private Result GO_HOME = Results.redirect(
//            routes.HomeController.list(0, "name", "asc", "")
//    );

    /**
     * Handle default path requests, redirect to computers list
     */
    public Result index() {
        return ok(views.html.site.home.render());
    }    
}
