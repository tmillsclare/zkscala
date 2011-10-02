package org.zkoss.zkscala.lib.models {
	
	import org.zkoss.zkplus.databind.BindingListModel

	/**
	 * This interface defines the methods used by DataBinder.
	 *
	 * @author Timothy Clare
	 */
	class BindingListModelList[T](seq : Seq[T]) extends ListModelList[T](seq) with BindingListModel {

		def this() = {
			this(null)
		}

		/** Returns index of the given object inside a ListModel.
		*	@param item The item to find
		*	@return The index of the item or -1 if it doesn't exist
		*/
		def indexOf(item : java.lang.Object) : Int = _list.indexOf(item)
	}
}