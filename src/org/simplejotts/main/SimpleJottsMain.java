package org.simplejotts.main;

import org.simplejotts.view.View;
import org.simplejotts.model.Model;
import org.simplejotts.controller.Controller;

public class SimpleJottsMain {
	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(model, view);
		view.showMainFrame();
	}
}
