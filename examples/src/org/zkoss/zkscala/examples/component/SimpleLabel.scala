package org.zkoss.zkscala.examples.component

import org.zkoss.zkscala.lib.utils.ScalaComponentUtils
import org.zkoss.zk.au.AuRequest
import org.zkoss.zk.ui.HtmlBasedComponent
import org.zkoss.zk.ui.event.Events
import org.zkoss.zk.ui.sys.ComponentCtrl._

import org.zkoss.zkscala.examples.events.ClearEvent

class SimpleLabel extends HtmlBasedComponent {
	ScalaComponentUtils.addClientEvent(classOf[SimpleLabel], ClearEvent.NAME, CE_IMPORTANT)
		
	var _value: String = ""
	var _cleared: Boolean = false
	
	def isCleared(): Boolean = _cleared
	def getValue(): String = _value
	
	def setValue(value: String) {
		if(!value.equals(_value)) {
			_value = value;
			smartUpdate("value", _value);
		}
	}
	
	protected override def renderProperties(renderer: org.zkoss.zk.ui.sys.ContentRenderer) {
		super.renderProperties(renderer)
		render(renderer, "value", _value)
	}
	
	override def service(request: AuRequest, everError: Boolean) {
		val cmd = request.getCommand()
		
		if (cmd.equals(ClearEvent.NAME)) {
			val evt = ClearEvent.getClearEvent(request);
			_cleared = evt.getCleared();
			Events.postEvent(evt);
		} else
			super.service(request, everError);
	}
}

