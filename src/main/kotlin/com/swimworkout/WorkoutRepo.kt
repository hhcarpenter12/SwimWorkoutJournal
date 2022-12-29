package com.swimworkout;

import SwimWorkout
import org.springframework.stereotype.Repository


@Repository
   class WorkoutRepo {

      var mAllSwimWorkout = mutableListOf<SwimWorkout>()

      init {
         val s1 = SwimWorkout(50, 10, "freestyle best average", "1:00", "Held 28.5s, need to work turns", "2022/12/29", 0)


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
