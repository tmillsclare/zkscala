package org.zkoss.zkscala.lib.models {
	
	import scala.collection.mutable.ListBuffer
	
	import collection.JavaConversions._

	import java.util.Collections
	import java.util.Comparator

	import org.zkoss.zul.event.ListDataEvent
	import org.zkoss.zk.ui.UiException

	class ListModelList[T](list : Seq[T]) extends AbstractListModel with org.zkoss.zul.ListModelExt {

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

		def clear() = {
			
			val listSize = _list.size

			if(listSize > 0) {
				_list.clear
				fireEvent(ListDataEvent.INTERVAL_REMOVED, 0, listSize)
			}
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

		//removing items from the list
		def remove(item : T) {
			
			val itemIndex = _list.indexOf(item)
			
			if(itemIndex > -1) {
				_list -= item
				fireEvent(ListDataEvent.INTERVAL_REMOVED, itemIndex, itemIndex)
			}
		}

		def -=(item : T) {
			remove(item)
		}

		//sorting for ListModelExt
		def sort(cmpr : java.util.Comparator[_], asc : Boolean) {
			
			//perform conversion to work around the API
			//TODO: Fix this when ZK 6 comes out
			var newComp : Comparator[T] = cmpr.asInstanceOf[java.util.Comparator[T]]

			Collections.sort(_list, newComp)
			fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1)
		}

		override def toString = _list.mkString(this.getClass.getSimpleName + "(", ",", ")")

	}
}