package tools.aqua.stars.testing

import tools.aqua.stars.core.evaluation.Predicate.Companion.predicate
import tools.aqua.stars.core.evaluation.VariablePredicate.Companion.predicate
import tools.aqua.stars.core.tsc.builder.tsc
import tools.aqua.stars.data.av.dataclasses.*
import tools.aqua.stars.logic.kcmftbl.firstorder.exists


val p2 = predicate<TickData>("P2") { tick ->
    true
}

val p3 = predicate<TickData, Pedestrian>("P3") { tick, pedestrians ->
    true
}

val p1 = predicate<TickData>("P1") { tick ->
    exists(tick.pedestrians) { pedestrian ->
        p3.holds(tick, pedestrian)
    }
}

val tsc = tsc<Actor, TickData, TickDataUnitSeconds, TickDataDifferenceSeconds> {
    any("root") {
        condition(p1)
    }
}