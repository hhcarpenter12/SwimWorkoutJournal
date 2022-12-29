import javax.persistence.*


@Entity
class SwimWorkout(
    var distance: Int? = null,
    var numReps: Int? = null,
    var description: String? = null,
    var intervalTime: String? = null,
    var workoutNotes: String? = null,
    var completionDate: String? = null,
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Int = 0)
