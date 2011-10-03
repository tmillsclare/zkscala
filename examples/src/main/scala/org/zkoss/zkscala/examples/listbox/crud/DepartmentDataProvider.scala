package org.zkoss.zkscala.examples.listbox.crud

object DepartmentDataProvider {

	var defaultDepartment = new Department("Support", "This is the support department")

	val departments = Seq(
		defaultDepartment,
		new Department("Marketing", "This is the marketing department"),
		new Department("RD", "This is the RD department"),
	    new Department("Accounting", "This is the accounting department"),
	    new Department("Law", "This is the law department")
	)	
}