package org.zkoss.zkscala.lib.models {
	
	import scala.collection.mutable.ListBuffer
	import org.zkoss.zul.event.ListDataEvent;
	import org.zkoss.zk.ui.UiException;

	class ListModelList[T](list : Seq[T]) extends AbstractListModel {

		protected var _list = ListBuffer.empty[T]

		if(list != null) {
			list.foreach(item => _list += item)
		}

		def this() = {
			this(null)
		}

		def removeRange(start : Int, end : Int) : Unit = {
			if(start > end)	 {
				throw new UiException("the start must be less than the end: " + start + ", end: " + end)
			}

			if(start == end) {
				return;
			}

			val listSize = _list.size
			if(listSize == start) {
				return;
			}

			val to = _list.remove(start, end - start)
			fireEvent(ListDataEvent.INTERVAL_REMOVED, start, end - start)
		}

		def getSize() : Int = _list.size

		def getElementAt(index : Int) : Object = {
			val returnValue = _list.apply(index)
			returnValue.asInstanceOf[java.lang.Object]
		}

		//adding items to the list
		def add(index : Int, item : T) = {	
			_list.insert(index, item)
			fireEvent(ListDataEvent.INTERVAL_ADDED, index, index)
		}

		def add(item : T) = {
			val il = _list.size
			_list += item
			fireEvent(ListDataEvent.INTERVAL_ADDED, il, il)
		}

		def +=(item : T) = {
			add(item)
		}

	}
}