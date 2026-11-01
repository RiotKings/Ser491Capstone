package com.example.robotcapstone.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TourState { READY, ACTIVE, PAUSED }

interface RobotController {
    /** Begin or continue driving toward the given stop */
    fun driveToStop(stopIndex: Int, onArrived: () -> Unit)

    /** Stop all motion immediately. */
    fun stopMotion()
}

/** Fake robot for development without hardware. Each "drive" takes a few seconds. */
class SimulatedRobot(
    private val scope: CoroutineScope,
    private val driveTimeMs: Long = 4000
) : RobotController {
    private var job: Job? = null

    override fun driveToStop(stopIndex: Int, onArrived: () -> Unit) {
        job?.cancel()
        job = scope.launch {
            delay(driveTimeMs)
            onArrived()
        }
    }

    override fun stopMotion() {
        job?.cancel()
        job = null
    }
}

class TourController(
    private val robot: RobotController,
    val totalStops: Int = 4
) {
    var state by mutableStateOf(TourState.READY)
        private set

    /** Index of the stop the robot is currently heading to */
    var currentStop by mutableIntStateOf(0)
        private set

    fun startTour() {
        if (state != TourState.READY) return
        currentStop = 0
        state = TourState.ACTIVE
        robot.driveToStop(currentStop, ::onArrived)
    }

    // REQ-3 + REQ-4
    fun pauseTour() {
        if (state != TourState.ACTIVE) return
        robot.stopMotion()          // stop first, then change state
        state = TourState.PAUSED   // currentStop is untouched, so progress is kept
    }

    // REQ-5
    fun resumeTour() {
        if (state != TourState.PAUSED) return
        state = TourState.ACTIVE
        robot.driveToStop(currentStop, ::onArrived)  // continue the same leg
    }

    private fun onArrived() {
        if (state != TourState.ACTIVE) return  // ignore late arrivals while paused
        if (currentStop < totalStops - 1) {
            currentStop++
            robot.driveToStop(currentStop, ::onArrived)
        } else {
            robot.stopMotion()
            state = TourState.READY  // placeholder; completion is REQ-23..26
        }
    }
}