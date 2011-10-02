/** Provides scala classes which fulfill ZK model requirements
  */
package org.zkoss.zkscala.lib.models {

	import scala.collection.mutable.ListBuffer

	import org.zkoss.zul.event.ListDataListener
	import org.zkoss.zul.event.ListDataEvent
	import org.zkoss.io.Serializables

	/**
 	* A skeletal implementation for ListModel. Based on ZK's counterpart
 	*
 	* @author Timothy Clare
 	*/
	abstract class AbstractListModel extends org.zkoss.zul.ListModel with org.zkoss.zkscala.lib.models.Selectable with java.io.Serializable {

		@transient
		var _listeners = ListBuffer.empty[ListDataListener]

		@transient
		private var lock : AnyRef = new Object()

		def getElementAt(index: Int) : Object
		def getSize() : Int

		/** Fires a ListDataEvent for all registered listener
	 	* (through {@link #addListDataListener}.
		*
		* [b]Note: you can invoke this method only in an event listener.[/b]
		*
		* @param evttype The Type of event
		* @param index0 The first index of the item which has changed (inclusive)
		* @param index1 The last index of the item which has changed (inclusive)
		*/
		protected def fireEvent(evttype : Int, index0 : Int, index1 : Int) = {
			val evt = new ListDataEvent(this, evttype, index0, index1);
			_listeners.foreach(listener => listener.onChange(evt))
		}

		/** Adds a data listener to the model
		*
		* @param dataListener The dataListener to be added
		*/
		def addListDataListener(dataListener : ListDataListener) = {
			if(dataListener == null)
				throw new NullPointerException()

			_listeners += dataListener
		}

		/** Removes a data listener to the model
		*
		* @param dataListener The dataListener to be removed
		*/
		def removeListDataListener(dataListener : ListDataListener) = {
			_listeners -= dataListener
		}


		private def writeObject(s : java.io.ObjectOutputStream) = {
			lock.synchronized {
				s.defaultWriteObject()

				Serializables.smartWrite(s, _listeners.asInstanceOf[java.util.List[Any]])
			}
		}

		private def readObject(s : java.io.ObjectInputStream) = {
			lock.synchronized {
				s.defaultReadObject()

				_listeners = ListBuffer.empty[ListDataListener]
				Serializables.smartRead(s, _listeners.asInstanceOf[java.util.List[Any]])
			}
		}

	}
}