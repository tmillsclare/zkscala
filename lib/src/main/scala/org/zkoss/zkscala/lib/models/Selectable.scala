package org.zkoss.zkscala.lib.models {

	import scala.collection.mutable
	import collection.JavaConversions._
	
	/**
	* Implements ZK's selectable interface enabling easy usage and conformance in other classes and traits
	*
	* @author Timothy Clare
	*/
	trait Selectable extends org.zkoss.zul.ext.Selectable {
		
		val _set = mutable.Set.empty[Object]

		/** Will retrieve a mutable {@link scala.collection.mutable.Set} of selected items
		*
		* @return the mutable {@link scala.collection.mutable.Set} of selected items
		*/
		def getSelection() : java.util.Set[Object]  = _set
		
		/** Adds a selected object to the set
		*
		* @param obj the obj to add
		*/
		def addSelection(obj : Object) = {
			_set += obj
		}	

		/** Removes a selected object from the set
		*
		* @param obj the obj to remove
		*/
		def removeSelection(obj : Object) = {
			_set -= obj
		}

		/** Clears the selections
		*
		*/
		def clearSelection() = {
			_set.clear()
		}
	}
}
