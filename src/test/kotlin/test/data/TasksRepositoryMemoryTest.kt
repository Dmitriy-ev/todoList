package test.data

import data.Priority
import data.Task
import data.TasksRepository
import data.TasksRepositoryMemory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

internal class TasksRepositoryMemoryTest {


    private lateinit var repository: TasksRepository

    @BeforeTest
    fun init() {
        repository = TasksRepositoryMemory().apply {
            addTask(Task(1, "Task1", Priority.HIGH, true))
            addTask(Task(2, "Task2", Priority.LOW))
            addTask(Task(3, "Task3", Priority.MEDIUM, true))
            addTask(Task(4, "Task4", Priority.HIGH))
            addTask(Task(5, "Task5", Priority.MEDIUM))
        }
    }

    @Test
    fun getAllTask() {
        assertEquals(5, repository.getTasks().size)
    }

    @Test
    fun getTaskWithFalseParameter() {
        assertEquals(3, repository.getTasks(false).size)
    }

    @Test
    fun addTask() {
        val newTask = Task(6, "Task6", Priority.LOW)
        repository.addTask(newTask)
        assertEquals(newTask, repository.getTasks().first { it.id == 6 })
    }

    @Test
    fun deleteTask() {
        assertEquals(5, repository.getTasks().size)
        repository.deleteTask(3)
        assertEquals(4, repository.getTasks().size)
    }

    @Test
    fun completeTask() {
        assertEquals(3, repository.getTasks(false).size)
        repository.completeTask(2)
        assertEquals(2, repository.getTasks(false).size)
    }

    @Test
    fun uncompleteTask() {
        assertEquals(3, repository.getTasks(false).size)
        repository.uncompleteTask(1)
        assertEquals(4, repository.getTasks(false).size)

    }
}