package org.zkoss.zkscala.examples.grid.databinding {

	import org.zkoss.zk.ui.util.GenericForwardComposer
	import org.zkoss.zk.ui.Component

	import org.zkoss.zkscala.lib.models.BindingListModelList

	import scala.reflect.BeanProperty

	class GridDatabindingController extends GenericForwardComposer {
		
		val titles = List("Code", "Bugs", "Docs", "Arts")

		val defaultContributor = new Contributor("Jon", "Art", titles.apply(0), 123)

		val myContributorList = Seq(
			defaultContributor,
			new Contributor("Kaleb", "Leonel", titles.apply(0), 321),
		    new Contributor("Balu", "Haben", titles.apply(0), 321),
		    new Contributor("Trey", "Wyatt", titles.apply(0), 323),
		    new Contributor("Balu", "Haben", titles.apply(0), 324),
		    new Contributor("Terry", "Tornado", titles.apply(0), 711),
		    new Contributor("Jesse", "Miles", titles.apply(1), 712),
		    new Contributor("Sadira", "Jobs", titles.apply(1), 713),
		    new Contributor("Jaquan", "Frederick", titles.apply(2), 451),
		    new Contributor("Avery", "Katrina", titles.apply(2), 453),
		    new Contributor("Heidi", "Nikolas", titles.apply(2), 455),
		    new Contributor("Katelyn", "Clara", titles.apply(2), 457),
		    new Contributor("Branden", "Shane", titles.apply(2), 459),
		    new Contributor("Dacey", "Obert", titles.apply(2), 450),
		    new Contributor("Julianna", "Allison", titles.apply(3), 643),
		    new Contributor("Rachel", "Elisabeth", titles.apply(3), 644),
		    new Contributor("Clarissa", "Francesca", titles.apply(3), 645),
		    new Contributor("Gabby", "Taffy", titles.apply(3), 646)
		)
		
		@BeanProperty var selected = defaultContributor

		val contributorListModel = new BindingListModelList[Contributor](myContributorList)
		val myTitleListModel = new BindingListModelList[String](titles)
		
		def allContributors() : BindingListModelList[Contributor] = contributorListModel
		def titleModel() : BindingListModelList[String] = myTitleListModel
	}
}