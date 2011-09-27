package org.zkoss.zkscala.examples.controller;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkscala.examples.events.ClearEvent;


public class TestController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8059837220012295812L;
	
	public void onClear$slabel(ClearEvent evt) {
		System.out.println(((ClearEvent) evt).getCleared());
	}
}
