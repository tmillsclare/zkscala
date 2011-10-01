package org.zkoss.zkscala.lib.utils;

import org.zkoss.zk.ui.AbstractComponent;

public final class ScalaComponentUtils extends AbstractComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2675254291097601229L;

	public static void addClientEvent(Class cls, String evtnm, int flags) {
		synchronized (cls) {
			AbstractComponent.addClientEvent(cls, evtnm, flags);
		}
	}
}
