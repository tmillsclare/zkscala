package org.zkoss.zkscala.examples.grid.databinding {

	import scala.reflect.BeanProperty

	class Contributor (@BeanProperty var firstName : String, @BeanProperty var lastName : String, @BeanProperty var title : String, @BeanProperty var extension : Int) {	
		def getFullName() : String = firstName + " " + lastName
	}
}