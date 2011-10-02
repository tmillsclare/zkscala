package org.zkoss.zkscala.examples.listbox.crud

import java.util.UUID

import scala.reflect.BeanProperty

class Department(@BeanProperty var name: String) {
	
	@BeanProperty val id : String = UUID.randomUUID.toString

	override def equals(obj : Any) : Boolean = {
		var equal : Boolean = obj match {
			case d : Department => {
				this.id.equals(d.id) && this.name.equals(d.name)
			}
			case _ => false
		}

		equal
	}

	override def hashCode = id.hashCode
}