/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package navigation

import base.SpecBase
import controllers.routes
import generators.Generators
import pages._
import models._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class NavigatorSpec extends SpecBase with ScalaCheckPropertyChecks with Generators {

  val navigator = new Navigator

  "Navigator" - {

    "in Normal mode" - {

      "must go from a page that doesn't exist in the route map to Index" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, NormalMode, answers)
              .mustBe(routes.IndexController.onPageLoad())
        }
      }

      "must go from Protector Type Page to Protector Name page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(ProtectorTypePage, NormalMode, answers)
              .mustBe(routes.ProtectorsNameController.onPageLoad(NormalMode))
        }
      }

      "must go from Protector Name page to Knows date of Birth Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(ProtectorsNamePage, NormalMode, answers)
              .mustBe(routes.DoYouKnowProtectorDateOfBirthController.onPageLoad(NormalMode))
        }
      }

      "must go from Knows date of Birth Page to Date of Birth Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowProtectorDateOfBirthPage, NormalMode, answers)
              .mustBe(routes.ProtectorDateOfBirthController.onPageLoad(NormalMode))
        }
      }

      "must go from Date of Birth Page to Do you know their nationality Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(ProtectorDateOfBirthPage, NormalMode, answers)
              .mustBe(routes.DoYouKnowTheirNationalityController.onPageLoad(NormalMode))
        }
      }

      "must go from Do you know their nationality Page to NationalityPage" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowTheirNationalityPage, NormalMode, answers)
              .mustBe(routes.ProtectorNationalityController.onPageLoad(NormalMode))
        }
      }

      "must go from NationalityPage to Country Same As Residence Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(ProtectorNationalityPage, NormalMode, answers)
              .mustBe(routes.ProtectorCountrySameAsResidenceController.onPageLoad(NormalMode))
        }
      }

      "must go from Country Same As Residence Page to Do You Know Their Country Of Residency Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(ProtectorCountrySameAsResidencePage, NormalMode, answers)
              .mustBe(routes.DoYouKnowTheirCountryOfResidencyController.onPageLoad(NormalMode))
        }
      }

      "must go from Do You Know Their Country Of Residency Page to What Is Their Country Of Residency Page" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowTheirCountryOfResidencyPage, NormalMode, answers)
              .mustBe(routes.WhatIsTheirCountryOfResidencyController.onPageLoad(NormalMode))
        }
      }
    }

    "in Check mode" - {

      "must go from a page that doesn't exist in the edit route map  to Check Your Answers" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, CheckMode, answers)
              .mustBe(routes.CheckYourAnswersController.onPageLoad())
        }
      }
    }
  }
}
