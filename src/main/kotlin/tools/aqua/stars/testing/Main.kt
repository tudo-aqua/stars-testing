/*
 * Copyright 2026 The STARS Coverage Significance Authors
 * SPDX-License-Identifier: Apache-2.0
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

package tools.aqua.stars.testing

import tools.aqua.stars.core.evaluation.TSCEvaluation
import tools.aqua.stars.core.metrics.evaluation.InvalidTSCInstancesPerTSCMetric
import tools.aqua.stars.core.metrics.evaluation.MissedTSCInstancesPerTSCMetric
import tools.aqua.stars.core.metrics.evaluation.ValidTSCInstancesPerTSCMetric
import tools.aqua.stars.core.tsc.builder.*
import tools.aqua.stars.data.av.dataclasses.*

fun main() {
  println("Starting STARS v2.0 testing...")

  // Create a simple TSC (Traffic Scenario Classifier)
  val tsc =
      tsc<Actor, TickData, TickDataUnitSeconds, TickDataDifferenceSeconds> {
        all("TSCRoot") {
          exclusive("Weather") {
            leaf("Clear")
            leaf("Cloudy")
            leaf("Rainy")
          }
        }
      }

  println("TSC created successfully!")
  println("Building projections...")

  println("Creating TSC Evaluation...")
  val evaluation =
      TSCEvaluation(
              writePlots = false,
              writePlotDataCSV = false,
              writeSerializedResults = false,
              compareToPreviousRun = false,
              tsc = tsc,
          )
          .apply {
            registerMetricProviders(
                ValidTSCInstancesPerTSCMetric<
                    Actor, TickData, TickDataUnitSeconds, TickDataDifferenceSeconds>(),
                InvalidTSCInstancesPerTSCMetric(),
                MissedTSCInstancesPerTSCMetric())
          }

  println("STARS v2.0 setup completed successfully!")
  println(
      "Note: To run actual evaluation, provide segments using evaluation.runEvaluation(segments)")
}
