package org.zkoss.zkscala.lib.test.models {

	import org.scalatest.FunSuite
	import org.scalatest.Tag
	import org.scalatest.BeforeAndAfterEachFunctions

	import org.zkoss.zkscala.lib.models.BindingListModelList

	import java.util.UUID

	class BindingListModelListSuite extends FunSuite with BeforeAndAfterEachFunctions {
		

		val listModel = new BindingListModelList[String]

		beforeEach {
			if(listModel.getSize > 0) {
				listModel.clear
			}
		}

		test("add") {
			val person = "Amy Walters"
			listModel.add(person)
			info(listModel.toString)

			assert(listModel.getSize == 1)
			assert(listModel.getElementAt(0).equals(person))
		}

		test("remove") {
			val person = "Amy Walters"
			listModel.add(person)
			info(listModel.toString)
			assert(listModel.getSize == 1)

			listModel.remove(person)
			info(listModel.toString)
			assert(listModel.getSize == 0)
		}

		test("removeRange") {
			
			for (i <- 0 until 10) {
				listModel.add(UUID.randomUUID.toString)
			}

			info(listModel.toString)
			assert(listModel.getSize == 10)

			listModel.removeRange(0, 5)
			info(listModel.toString)
			assert(listModel.getSize == 5)
		}

		test("clear") {
			for (i <- 0 until 10) {
				listModel.add(UUID.randomUUID.toString)
			}

			info(listModel.toString)
			assert(listModel.getSize == 10)

			listModel.clear
			info(listModel.toString)
			assert(listModel.getSize == 0)
		}

		test("indexOf") {
			pending
		}
		
	}
}