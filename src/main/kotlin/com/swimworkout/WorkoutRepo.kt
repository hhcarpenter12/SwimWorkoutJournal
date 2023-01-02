package com.swimworkout;

import SwimWorkout
import org.springframework.stereotype.Repository
import java.util.*


@Repository
   class WorkoutRepo {

      var mAllSwimWorkout = mutableListOf<SwimWorkout>()

      init {
         val s1 = SwimWorkout(50, 10, "freestyle best average", "1:30", "Held 28.5s, need to work turns", "2022/12/29", 500, "10:00", UUID.randomUUID())
         mAllSwimWorkout.add(s1)
      }

      fun allTimeYardage(): Int {
         var total = 0;

         for (swim in getAllWorkouts())
         {
            total += swim.totalYardage
         }

         return total
      }

      fun allTimeDuration(): String {
         var totalTime = 0

         for (swim in getAllWorkouts()) {

            val seconds = swim.estimatedTime.substringAfterLast(":").toInt()
            val minutes = swim.estimatedTime.substringBeforeLast(":").toInt()

            totalTime += (((minutes * 60) + (seconds)))
         }

         val min = totalTime / 60
         val sec = totalTime % 60

         if (sec < 10)
         {
            return min.toString() + ":" + "0" + sec.toString()
         }


         return min.toString() + ":" + sec.toString()
      }

      /**
       * Retrieve all Workout.
       */
      fun getAllWorkouts(): List<SwimWorkout>{
         return mAllSwimWorkout
      }


      fun deleteWorkout(id: UUID): List<SwimWorkout>
      {
         var removedCount = 0;

         for (workout in mAllSwimWorkout)
         {
            if (workout.id == id)
            {
               break
            }

            removedCount++
         }

         if (mAllSwimWorkout.size != 0) {
            mAllSwimWorkout.removeAt(removedCount)
         }

         return mAllSwimWorkout
      }


      /**
       * Adding new Workout.
       */
      fun addWorkout(passSwimWorkout:SwimWorkout): List<SwimWorkout>{
         if (passSwimWorkout.completionDate.isNullOrEmpty())
         {
            passSwimWorkout.completionDate = "N/A"
         }

         if (passSwimWorkout.workoutNotes.isNullOrEmpty())
         {
            passSwimWorkout.workoutNotes = "N/A"
         }

         val seconds = passSwimWorkout.intervalTime.substringAfterLast(":").toInt()
         val minutes = passSwimWorkout.intervalTime.substringBeforeLast(":").toInt()

         val estTime = (((minutes * 60) + (seconds)) * passSwimWorkout.numReps)

         val min = estTime / 60
         val sec = estTime % 60

         passSwimWorkout.estimatedTime = min.toString() + ":" + sec.toString()

         passSwimWorkout.totalYardage = passSwimWorkout.numReps * passSwimWorkout.distance

         passSwimWorkout.id = UUID.randomUUID()

         mAllSwimWorkout.add(passSwimWorkout)

         return mAllSwimWorkout
      }
}
