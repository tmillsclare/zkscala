package org.zkoss.zkscala.lib.models {
	
	import org.zkoss.zkplus.databind.BindingListModel

	class BindingListModelList[T](seq : Seq[T]) extends ListModelList[T](seq) with BindingListModel {

		def this() = {
			this(null)
		}

		def indexOf(item : java.lang.Object) : Int = _list.indexOf(item)
	}
}