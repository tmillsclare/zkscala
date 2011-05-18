package org.zkoss.zkscala.utils;

import org.zkoss.zk.ui.AbstractComponent;

public final class ScalaComponentUtils extends AbstractComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2675254291097601229L;

	@SuppressWarnings("unchecked")
	public static void addClientEvent(Class cls, String evtnm, int flags) {
		synchronized (cls) {
			AbstractComponent.addClientEvent(cls, evtnm, flags);
		}
	}
}
