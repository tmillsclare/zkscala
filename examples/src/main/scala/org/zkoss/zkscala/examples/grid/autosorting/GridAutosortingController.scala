package org.zkoss.zkscala.examples.grid.autosorting {
	
	import org.zkoss.zk.ui.util.GenericForwardComposer
	import org.zkoss.zkscala.lib.models.BindingListModelList

	import org.zkoss.zkscala.examples.grid.databinding.{Contributor, ContributorDataProvider}

	class GridAutosortingController extends GenericForwardComposer {
		val contributorListModel = new BindingListModelList[Contributor](ContributorDataProvider.myContributorList)
		def allContributors() : BindingListModelList[Contributor] = contributorListModel
	}	
}