package pet.project.sarafan.repo

import nu.studer.sample.Tables.MESSAGES
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import pet.project.sarafan.domain.Message
import java.util.*

@Repository
class MessageRepo(val context: DSLContext) {

    fun findAll(): MutableList<Message>? {
        return context
                .selectFrom(MESSAGES)
                .fetchInto(Message::class.java)
    }

    fun findById(uuid: UUID?): Optional<Message> {
        val res = context
                .selectFrom(MESSAGES)
                .where(MESSAGES.ID.eq(uuid))
                .fetchInto(Message::class.java)
        return Optional.ofNullable(res[0])
    }

    fun save(message: Message) {
        context
                .insertInto(MESSAGES)
                .columns(MESSAGES.ID, MESSAGES.TEXT)
                .values(message.id, message.text)
                .execute()
    }

    fun deleteById(id: UUID?): Boolean {
        val res = context
                .deleteFrom(MESSAGES)
                .where(MESSAGES.ID.eq(id))
                .execute()
        return res != 0
    }
}