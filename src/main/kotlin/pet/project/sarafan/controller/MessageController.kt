package pet.project.sarafan.controller

import org.springframework.web.bind.annotation.*
import pet.project.sarafan.domain.Message
import pet.project.sarafan.exception.NotFoundException
import pet.project.sarafan.repo.MessageRepo
import java.util.*

@RestController
@RequestMapping("messages")
class MessageController(private val messageRepo: MessageRepo) {

    @GetMapping
    fun getMessages(): MutableList<Message>? {
        return messageRepo.findAll()
    }

    @GetMapping("/{id}")
    fun getMessageById(@PathVariable id: String): Message {
        return messageRepo.findById(UUID.fromString(id)).orElseThrow { NotFoundException() }
    }

    @PostMapping
    fun createMessage(@RequestBody message: Message): Message {
        message.id = UUID.randomUUID()
        messageRepo.save(message)

        return message
    }

    @PutMapping("/{id}")
    fun updateMessage(@RequestBody message: Message, @PathVariable id: String): Message {
        messageRepo.findById(UUID.fromString(id)).ifPresent {
            it.text = message.text
            messageRepo.save(it)
        }

        return messageRepo.findById(UUID.fromString(id)).orElseThrow { NotFoundException() }
    }

    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: String) {
        messageRepo.deleteById(UUID.fromString(id))
    }
}