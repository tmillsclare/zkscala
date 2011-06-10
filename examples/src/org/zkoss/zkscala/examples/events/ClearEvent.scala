package org.zkoss.zkscala.examples.events

import org.zkoss.zk.au.{AuRequest, AuRequests}

class ClearEvent(val name: String, val component: org.zkoss.zk.ui.Component, val cleared: Boolean) extends org.zkoss.zk.ui.event.Event(name, component, cleared) {
	def getCleared() : Boolean = cleared
	
}

object ClearEvent {
	def getClearEvent(request: AuRequest) : ClearEvent = {
		val comp = request.getComponent()
		val map = request.getData()
		
		var cleared = AuRequests.getBoolean(map, "cleared")
		new ClearEvent(request.getCommand(), comp, cleared)
	}
	
	val NAME: String =  "onClear"
}