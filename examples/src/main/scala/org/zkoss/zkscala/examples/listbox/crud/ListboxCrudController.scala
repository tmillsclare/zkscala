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
		var txtDepartmentDescription: Textbox = null
		var lstDepartment : Listbox = null
		
		val listModel = new BindingListModelList[Department](DepartmentDataProvider.departments)
		def getAllDepartments = listModel

		@BeanProperty var currentDepartment : Department = DepartmentDataProvider.departments.apply(0)

		def onClick$btnAddDepartment = {
			val departmentName = txtDepartmentName.getText
			val departmentDescription = txtDepartmentDescription.getText

			if(departmentName.equals("") || departmentDescription.equals("")) {
				Clients.alert("Please enter a department name and description")
			} else {
				val department = new Department(departmentName, departmentDescription)
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