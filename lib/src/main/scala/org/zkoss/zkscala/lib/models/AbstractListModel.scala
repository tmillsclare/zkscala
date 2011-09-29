package org.zkoss.zkscala.lib.models {

	import scala.collection.mutable.ListBuffer
	import org.zkoss.zul.event.ListDataListener
	import org.zkoss.zul.event.ListDataEvent

	abstract class AbstractListModel extends org.zkoss.zul.ListModel with org.zkoss.zkscala.lib.models.Serializable with org.zkoss.zkscala.lib.models.Selectable {

		@transient
		var _listeners = ListBuffer.empty[ListDataListener]

		def getElementAt(index: Int) : Object
		def getSize() : Int

		protected def fireEvent(evttype : Int, index0 : Int, index1 : Int) = {
			val evt = new ListDataEvent(this, evttype, index0, index1);
			_listeners.foreach(listener => listener.onChange(evt))
		}

		def addListDataListener(dataListener : ListDataListener) = {
			if(dataListener == null)
				throw new NullPointerException()

			_listeners += dataListener
		}

		def removeListDataListener(dataListener : ListDataListener) = {
			_listeners -= dataListener
		}

	}
}