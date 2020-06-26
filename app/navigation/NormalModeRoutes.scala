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

import controllers.routes
import models.{NormalMode, UserAnswers}
import pages.{Page, ProtectorTypePage, ProtectorsNamePage}
import play.api.mvc.Call

object NormalModeRoutes {
  val normalRoutes: Page => UserAnswers => Call = {
    case ProtectorTypePage => _ => routes.ProtectorsNameController.onPageLoad(NormalMode)
    case ProtectorsNamePage => _ => routes.DoYouKnowProtectorDateOfBirthController.onPageLoad(NormalMode)
    case _ => _ => routes.IndexController.onPageLoad()
  }
}
