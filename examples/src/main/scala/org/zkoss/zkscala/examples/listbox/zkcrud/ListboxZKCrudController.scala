package org.zkoss.zkscala.examples.listbox.zkcrud {
	
	import scala.reflect.BeanProperty
	import collection.JavaConversions._

	import org.zkoss.zul.Listbox
	import org.zkoss.zul.Textbox

	import org.zkoss.zk.ui.util.Clients
	import org.zkoss.zk.ui.util.GenericForwardComposer

	import org.zkoss.zkscala.lib.models.BindingListModelList

	import org.zkoss.zkscala.examples.listbox.crud.Department

	class ListboxZKCrudController extends GenericForwardComposer {
		
		//autowired by ZK
		var txtDepartmentName : Textbox = null
		var lstDepartment : Listbox = null

		var defaultDepartment = new Department("Support")

		var myContributorList = new scala.collection.mutable.ListBuffer[Department]()

		val mySeq = Seq(
			defaultDepartment,
			new Department("Marketing"),
			new Department("RD"),
		    new Department("Accounting"),
		    new Department("Law")
		)

		myContributorList ++= mySeq
		
		//create a live data model
		val listModel = new org.zkoss.zkplus.databind.BindingListModelList(myContributorList, true)
		def getAllDepartments = listModel

		@BeanProperty var currentDepartment : Department = defaultDepartment

		def onClick$btnAddDepartment = {
			val departmentName = txtDepartmentName.getText

			if(departmentName.equals("")) {
				Clients.alert("Please enter a department Name")
			} else {
				val department = new Department(departmentName)
				/*

				As the list is live you can now use either:

				listModel.add(department) or
				myContributorList += department

				to append to the list, behold the flexibility of Java, Scala & ZK
				*/

				myContributorList += department
			}
		}

		def onClick$btnDeleteDepartment = {
			if(lstDepartment.getSelectedItem != null) {
				val department = lstDepartment.getSelectedItem.getValue.asInstanceOf[Department]
				listModel.remove(department)
			} else {
				Clients.alert("Please select a department")
			}
		}
	}
}