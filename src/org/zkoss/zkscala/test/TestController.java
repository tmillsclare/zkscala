package org.zkoss.zkscala.test;

import org.zkoss.zk.ui.util.GenericForwardComposer;

import examples.com.foo.events.ClearEvent;

public class TestController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8059837220012295812L;
	
	public void onClear$slabel(ClearEvent evt) {
		System.out.println(((ClearEvent) evt).getCleared());
	}
}
