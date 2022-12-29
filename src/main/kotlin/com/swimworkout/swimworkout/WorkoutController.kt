package com.swimworkout.swimworkout

import SwimWorkout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import kotlin.collections.List
import kotlin.collections.MutableMap
import kotlin.collections.set


@Controller
class WorkoutController {

    @Autowired
    val workoutsRepo = WorkoutRepo()

    @GetMapping("/")
    fun displayWorkouts(model: MutableMap<String?, Any?>): ModelAndView? {
        val swimworkouts: List<SwimWorkout> = workoutsRepo.getAllWorkouts()
        model["swimworkouts"] = swimworkouts
        return ModelAndView("index", model)
    }

    @PostMapping("/add")
    fun createNewWorkout(model: MutableMap<String?, Any?>, @ModelAttribute swimWorkout: SwimWorkout): ModelAndView? {
        workoutsRepo.addWorkout(swimWorkout);
        val swimworkouts: List<SwimWorkout> = workoutsRepo.getAllWorkouts()
        model["swimworkouts"] = swimworkouts
        return ModelAndView("redirect:/")
    }

    @GetMapping("/delete/{id}")
    fun deleteWorkout(model: MutableMap<String?, Any?>, @PathVariable("id") id: Int): ModelAndView?
    {
        workoutsRepo.deleteWorkout(id)
        val swimworkouts: List<SwimWorkout> = workoutsRepo.getAllWorkouts()
        model["swimworkouts"] = swimworkouts
        return ModelAndView("redirect:/")
    }
}
