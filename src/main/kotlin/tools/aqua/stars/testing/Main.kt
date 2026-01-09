package tools.aqua.stars.testing

import tools.aqua.stars.core.evaluation.TSCEvaluation
import tools.aqua.stars.core.metric.metrics.evaluation.*
import tools.aqua.stars.core.tsc.builder.*
import tools.aqua.stars.data.av.dataclasses.*

/**
 * Main entry point for STARS testing.
 * This demonstrates the STARS v2.0 creation process.
 */
fun main(args: Array<String>) {
    println("Starting STARS v2.0 testing...")
    
    // Create a simple TSC (Traffic Scenario Classifier)
    val tsc = tsc<Actor, TickData, Segment, TickDataUnitSeconds, TickDataDifferenceSeconds> {
        all("TSCRoot") {
            projections {
                projectionRecursive("default")
            }
            
            exclusive("Weather") {
                leaf("Clear")
                leaf("Cloudy")
                leaf("Rainy")
            }
        }
    }
    
    println("TSC created successfully!")
    println("Building projections...")
    
    val projections = tsc.buildProjections(emptyList())
    projections.forEach { projection ->
        println("Projection: $projection")
        println("Possible TSC instances: ${projection.possibleTSCInstances.size}")
    }
    
    println("Creating TSC Evaluation...")
    val evaluation = TSCEvaluation(
        tscList = projections,
        writePlots = false,
        writePlotDataCSV = false,
        writeSerializedResults = false,
        compareToBaselineResults = false,
        compareToPreviousRun = false
    ).apply {
        registerMetricProviders(
            ValidTSCInstancesPerTSCMetric<
                Actor,
                TickData,
                Segment,
                TickDataUnitSeconds,
                TickDataDifferenceSeconds
            >(),
            InvalidTSCInstancesPerTSCMetric(),
            MissedTSCInstancesPerTSCMetric()
        )
    }
    
    println("STARS v2.0 setup completed successfully!")
    println("Note: To run actual evaluation, provide segments using evaluation.runEvaluation(segments)")
}
