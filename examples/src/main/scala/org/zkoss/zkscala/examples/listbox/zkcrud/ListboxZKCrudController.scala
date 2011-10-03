package org.zkoss.zkscala.examples.listbox.zkcrud {
	
	import scala.reflect.BeanProperty
	import collection.JavaConversions._

	import org.zkoss.zul.Listbox
	import org.zkoss.zul.Textbox

	import org.zkoss.zk.ui.util.Clients
	import org.zkoss.zk.ui.util.GenericForwardComposer

	import org.zkoss.zkscala.lib.models.BindingListModelList

	import org.zkoss.zkscala.examples.listbox.crud.Department
	import org.zkoss.zkscala.examples.listbox.crud.DepartmentDataProvider

	class ListboxZKCrudController extends GenericForwardComposer {
		
		//autowired by ZK
		var txtDepartmentName : Textbox = null
		var txtDepartmentDescription: Textbox = null
		var lstDepartment : Listbox = null

		var myContributorList = new scala.collection.mutable.ListBuffer[Department]()
		myContributorList ++= DepartmentDataProvider.departments
		
		//create a live data model
		val listModel = new org.zkoss.zkplus.databind.BindingListModelList(myContributorList, true)
		def getAllDepartments = listModel

		@BeanProperty var currentDepartment : Department = DepartmentDataProvider.departments.apply(0)

		def onClick$btnAddDepartment = {
			val departmentName = txtDepartmentName.getText
			val departmentDescription = txtDepartmentDescription.getText


			if(departmentName.equals("") || departmentDescription.equals("")) {
				Clients.alert("Please enter a department name and description")
			} else {
				val department = new Department(departmentName, departmentDescription)

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