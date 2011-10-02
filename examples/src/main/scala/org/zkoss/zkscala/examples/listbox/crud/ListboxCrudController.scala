package org.zkoss.zkscala.examples.listbox.crud {
	
	import scala.reflect.BeanProperty

	import org.zkoss.zul.Listbox
	import org.zkoss.zul.Textbox

	import org.zkoss.zk.ui.util.Clients
	import org.zkoss.zk.ui.util.GenericForwardComposer

	import org.zkoss.zkscala.lib.models.BindingListModelList

	class ListboxCrudController extends GenericForwardComposer {
		
		//autowired by ZK
		var txtDepartmentName : Textbox = null
		var lstDepartment : Listbox = null

		var defaultDepartment = new Department("Support")

		val myContributorList = Seq(
			defaultDepartment,
			new Department("Marketing"),
			new Department("RD"),
		    new Department("Accounting"),
		    new Department("Law")
		)
		
		val listModel = new BindingListModelList[Department](myContributorList)
		def getAllDepartments = listModel

		@BeanProperty var currentDepartment : Department = defaultDepartment

		def onClick$btnAddDepartment = {
			val departmentName = txtDepartmentName.getText

			if(departmentName.equals("")) {
				Clients.alert("Please enter a department Name")
			} else {
				val department = new Department(departmentName)
				listModel += department
			}
		}

		def onClick$btnDeleteDepartment = {
			if(lstDepartment.getSelectedItem != null) {
				val department = lstDepartment.getSelectedItem.getValue.asInstanceOf[Department]
				listModel -= department
			} else {
				Clients.alert("Please select a department")
			}
		}
	}
}