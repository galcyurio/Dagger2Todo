package com.github.galcyurio.todo.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity
import com.github.galcyurio.todo.util.MainCoroutineRule
import com.github.galcyurio.todo.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule = MainCoroutineRule()

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var getTasks: GetTasksUseCase

    private val task1 = TaskEntity(id = 1, title = "foo1", description = "bar1", isCompleted = false)
    private val task2 = TaskEntity(id = 2, title = "foo2", description = "bar2", isCompleted = true)

    @Before
    fun setUp() {
        getTasks = mockk()
        coEvery { getTasks() } returns listOf(task1, task2)
        tasksViewModel = TasksViewModel(getTasks)
    }

    @Test
    fun `생성되면 업무 목록을 불러와야 한다`() {
        val actual = tasksViewModel.tasks.getOrAwaitValue()
        assertThat(actual).contains(task1, task2)
    }
}
