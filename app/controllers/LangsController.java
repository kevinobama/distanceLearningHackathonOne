// package controllers;

// import play.data.Form;

// import play.data.FormFactory;
// import play.libs.concurrent.HttpExecutionContext;
// import play.mvc.*;

// import repository.*;
// import models.*;
// import java.util.*;

// import io.ebean.*;

// import java.io.IOException;
// import java.text.*;

// import helpers.HoldingHelper;
// import redis.clients.jedis.Jedis;
// import static play.libs.Json.*;

// import com.fasterxml.jackson.core.JsonParseException;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.JsonNode;
// import repository.*;

// import helpers.*;
// import play.libs.ws.*;
// import javax.inject.Inject;
// //import javax.xml.transform.Result;
// import java.util.concurrent.CompletionStage;

// import java.util.*;
// import play.libs.ws.WSRequest;
// import play.libs.Json;
// import play.libs.ws.StandaloneWSResponse;
// import repository.*;

// import play.i18n.Lang;
// import play.i18n.Langs;
// import play.i18n.Messages;
// import play.i18n.MessagesApi;

// /**
//  * Manage a database of LangsController
//  */
// public class LangsController extends BaseController {

// 	private final Langs langs;
// 	private Messages messages;
	
// 	@Inject
// 	public LangsController(Langs langs) {
// 		this.langs = langs;
// 	}
	
// 	public Result index(String lang) {		  		
// 		//---------------------------------------
// 		//ctx().changeLang(new Lang(Lang.forCode("cn")));
// 		ctx().changeLang(new Lang(Lang.forCode(lang)));
				
// 		//return Results.redirect(routes.HoldingsController.index());
// 		this.messages = Http.Context.current().messages();
		
// 		String redirectUrl ="";
		
// 		return ok(views.html.common.success.render("success", redirectUrl, this.messages));
// 	}
			
// }
