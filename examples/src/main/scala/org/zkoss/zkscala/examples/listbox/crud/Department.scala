package org.zkoss.zkscala.examples.listbox.crud

import java.util.UUID

import scala.reflect.BeanProperty

class Department(@BeanProperty var name: String, @BeanProperty var description : String) {
	
	@BeanProperty val id : Int = DepartmentIdProvider.nextId

	override def equals(obj : Any) : Boolean = {
		var equal : Boolean = obj match {
			case d : Department => {
				this.id.equals(d.id)
			}
			case _ => false
		}

		equal
	}

	override def hashCode = id.hashCode
}

object DepartmentIdProvider {
	var id = -1
	def nextId : Int = {
		id += 1
		id
	}
}