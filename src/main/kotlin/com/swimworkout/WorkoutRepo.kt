package com.swimworkout;

import SwimWorkout
import antlr.StringUtils
import org.springframework.stereotype.Repository
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Repository
   class WorkoutRepo {

      var mAllSwimWorkout = mutableListOf<SwimWorkout>()

      init {
         val s1 = SwimWorkout(50, 10, "freestyle best average", "1:30", "Held 28.5s, need to work turns", "2022/12/29", 500, "10:00", 0)


         mAllSwimWorkout.add(s1)
      }

      /**
       * Retrieve all Workout.
       */
      fun getAllWorkouts(): List<SwimWorkout>{
         return mAllSwimWorkout
      }


      fun deleteWorkout(id: Int): List<SwimWorkout>
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

         passSwimWorkout.estimatedTime = min.toString() + " minutes " + sec.toString() + " seconds";

         passSwimWorkout.totalYardage = passSwimWorkout.numReps * passSwimWorkout.distance

         passSwimWorkout.id = findId()
         mAllSwimWorkout.add(passSwimWorkout)

         return mAllSwimWorkout
      }

   fun findId(): Int {
      if (mAllSwimWorkout.size != 0) {

         val myInt: Int =  mAllSwimWorkout.get(mAllSwimWorkout.size - 1).id + 1

         return myInt
      }

      return 0;
   }
}
