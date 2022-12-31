import java.util.*
import javax.persistence.*


@Entity
class SwimWorkout(
    var distance: Int = 0,
    var numReps: Int = 0,
    var description: String? = null,
    var intervalTime: String = "",
    var workoutNotes: String? = null,
    var completionDate: String? = null,
    var totalYardage: Int = 0,
    var estimatedTime: String = "",
    @Id
    @Column(name = "id", nullable = true)
    var id: UUID?)

