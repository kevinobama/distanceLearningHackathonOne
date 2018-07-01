package controllers;


import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import javax.inject.Inject;

/**
 * Manage a database of home
 */
public class DashboardController extends BaseController {
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public DashboardController(FormFactory formFactory,
                               HttpExecutionContext httpExecutionContext) {
        this.formFactory = formFactory;         
        this.httpExecutionContext = httpExecutionContext;
    }

    /**
     * Handle default path requests, redirect to computers list
     */
    public Result index() {
        return ok(views.html.dashboard.index.render());
    }

    /**
     * Handle default path requests, redirect to computers list
     */
    public Result teaching(String teacher) {
        return ok(views.html.dashboard.teaching.render());
    }
}
