package pet.project.sarafan.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pet.project.sarafan.domain.Message
import java.util.*

@Repository
interface MessageRepo : JpaRepository<Message, UUID> {
}