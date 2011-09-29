package org.zkoss.zkscala.lib.models {

	import scala.collection.mutable
	import collection.JavaConversions._
	
	trait Selectable extends org.zkoss.zul.ext.Selectable {
		
		val _set = mutable.Set.empty[Object]

		def getSelection() : java.util.Set[Object]  = _set
		
		def addSelection(obj : Object) = {
			_set += obj
		}	

		def removeSelection(obj : Object) = {
			_set -= obj
		}

		def clearSelection() = {
			_set.clear()
		}
	}
}
