package org.zkoss.zkscala.examples.grid.databinding {

	import org.zkoss.zk.ui.util.GenericForwardComposer
	import org.zkoss.zk.ui.Component

	import org.zkoss.zkscala.lib.models.BindingListModelList

	import scala.reflect.BeanProperty

	class GridDatabindingController extends GenericForwardComposer {
		
		@BeanProperty var selected = ContributorDataProvider.myContributorList.apply(0)

		val contributorListModel = new BindingListModelList[Contributor](ContributorDataProvider.myContributorList)
		val myTitleListModel = new BindingListModelList[String](ContributorDataProvider.titles)
		
		def allContributors() : BindingListModelList[Contributor] = contributorListModel
		def titleModel() : BindingListModelList[String] = myTitleListModel
	}
}