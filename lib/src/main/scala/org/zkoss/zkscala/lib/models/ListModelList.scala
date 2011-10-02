package org.zkoss.zkscala.lib.models {
	
	import scala.collection.mutable.ListBuffer
	
	import collection.JavaConversions._

	import java.util.Collections
	import java.util.Comparator

	import org.zkoss.zul.event.ListDataEvent
	import org.zkoss.zk.ui.UiException

	/**
	* This is the ListModel as a {@link scala.collection.Seq} to be used with the Grid and ListBox.
	* Add or remove the contents of this model as a List would cause the associated Listbox to change accordingly.
	*
	* <p>For more information, please refer to
	* <a href="http://books.zkoss.org/wiki/ZK_Developer%27s_Reference/MVC/Model/List_Model">ZK Developer's Reference: List Model</a>
	*
	* @author Timothy Clare
	* @constructor create a new ListModelList using the Seq given, this will always create a copy of the data
	* @see BindingListModelList
	*/
	class ListModelList[T](var list : Seq[T] = null) extends AbstractListModel with org.zkoss.zul.ListModelExt {

		protected var _list = ListBuffer.empty[T]

		if(list != null) {
			list.foreach(item => _list += item)
		}

		/** 
	 	* Remove from fromIndex(inclusive) to toIndex(exclusive). If fromIndex equals toIndex, 
	 	* this methods do nothing.
		*
		* @param start the start index (inclusive) to be removed.
		* @param end the end index (exclusive) to be removed.
		*/
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

		/** 
	 	* Clears the model
		*
		*/
		def clear() = {
			
			val listSize = _list.size

			if(listSize > 0) {
				_list.clear
				fireEvent(ListDataEvent.INTERVAL_REMOVED, 0, listSize)
			}
		}

		/**
		* Return the size of internal {@link scala.collection.mutable.ListBuffer}
		*
		* @return the size of the list
		*/
		def getSize() : Int = _list.size

		def getElementAt(index : Int) : Object = {
			val returnValue = _list.apply(index)
			returnValue.asInstanceOf[java.lang.Object]
		}

		/**
		* Add item to the model
		*
		* @param index add the item at the relevant index
		* @param item the item to add to the model
		*/
		def add(index : Int, item : T) = {	
			_list.insert(index, item)
			fireEvent(ListDataEvent.INTERVAL_ADDED, index, index)
		}

		/**
		* Add item to the model
		*
		* @param item the item to add to the model
		*/
		def add(item : T) = {
			val il = _list.size
			_list += item
			fireEvent(ListDataEvent.INTERVAL_ADDED, il, il)
		}

		/**
		* Add item to the model
		*
		* @param item the item to add to the model
		*/
		def +=(item : T) = {
			add(item)
		}

		/**
		* Remove item to the model
		*
		* @param item the item to remove from the model
		*/
		def remove(item : T) {
			
			val itemIndex = _list.indexOf(item)
			
			if(itemIndex > -1) {
				_list -= item
				fireEvent(ListDataEvent.INTERVAL_REMOVED, itemIndex, itemIndex)
			}
		}

		/**
		* Remove item to the model
		*
		* @param item the item to remove from the model
		*/
		def -=(item : T) {
			remove(item)
		}

		/** Sorts the data.
		*
		* @param cmpr the comparator.
		* @param ascending whether to sort in the ascending order.
		* It is ignored since this implementation uses cmprt to compare.
		*/
		def sort(cmpr : java.util.Comparator[_], asc : Boolean) {
			
			//perform conversion to work around the API
			//TODO: Fix this when ZK 6 comes out
			var newComp : Comparator[T] = cmpr.asInstanceOf[java.util.Comparator[T]]

			Collections.sort(_list, newComp)
			fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1)
		}

		/** Debugging purposes only
		*
		* @return ascending whether to sort in the ascending order.
		*/
		override def toString = _list.mkString(this.getClass.getSimpleName + "(", ",", ")")

	}
}