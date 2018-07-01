package controllers;

import play.mvc.Controller;

public class BaseController extends Controller {

	public static void echo(Object any) {
		System.out.println(any);
	}
}
