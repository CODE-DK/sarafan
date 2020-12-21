package pet.project.sarafan.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue
    var id: UUID? = null,
    var text: String? = null
)