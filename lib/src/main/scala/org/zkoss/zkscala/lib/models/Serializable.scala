package org.zkoss.zkscala.lib.models {
	
	import org.zkoss.io.Serializables
	import scala.collection.mutable.LinkedList
	import collection.JavaConversions._

	trait Serializable extends java.io.Serializable {
		@transient
		private var _listeners = LinkedList.empty[Object]

		@transient
		private var lock : AnyRef = new Object()

		private def writeObject(s : java.io.ObjectOutputStream) = {
			lock.synchronized {
				s.defaultWriteObject()

				Serializables.smartWrite(s, _listeners)
			}
		}

		private def readObject(s : java.io.ObjectInputStream) = {
			lock.synchronized {
				s.defaultReadObject()

				_listeners = LinkedList.empty[Object]
				Serializables.smartRead(s, _listeners)
			}
		}
	}
}