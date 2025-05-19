package org.simplejotts.main;

import org.simplejotts.view.View;
import org.simplejotts.model.Model;
import org.simplejotts.controller.Controller;

public class SimpleJottsMain {
	public static void main(String[] args) {
		System.out.println("Test");
		View view = new View();
		// view.showMainFrame();
		Model model = new Model();
		Controller controller = new Controller(model, view);
		view.showMainFrame();
	}
}
