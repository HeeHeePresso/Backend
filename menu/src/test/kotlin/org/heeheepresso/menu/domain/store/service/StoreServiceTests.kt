package org.heeheepresso.menu.domain.store.service

import org.heeheepresso.menu.domain.store.model.Store
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StoreServiceTests (
        @Autowired val sut: StoreService
) {
    @BeforeEach
    fun initialize() {
        sut.deleteAll()
    }

    @Test
    fun test_save() {
        //given
        val input = Store(
                id = null,
                name = "수원영통점",
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                address = "경기도 수원시 팔달구 394-12",
        )

        //when
        val result = sut.save(input)

        //then
        assertNotNull(result)
        assertEquals(Store::class.java, result.javaClass)
        assertNotNull(result.id)
    }

    @Test
    fun test_findById() {
        //given
        val input = Store(
                id = null,
                name = "수원영통점",
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                address = "경기도 수원시 팔달구 394-12",
        )
        val savedInput = sut.save(input)

        //when
        val result = sut.findById(savedInput.id!!)

        //then
        assertEquals(input.name, result.name)
        assertEquals(input.thumbnailUrl, result.thumbnailUrl)
        assertEquals(input.address, result.address)
    }

    @Test
    fun test_findAll() {
        //given
        val input = Store(
                id = null,
                name = "수원영통점",
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                address = "경기도 수원시 팔달구 394-12",
        )
        sut.save(input)

        //when
        val result = sut.findAll()

        //then
        assertEquals(1, result.size)
    }

    @Test
    fun test_findAllWhenEmpty() {
        //when
        val result = sut.findAll()

        //then
        assertEquals(0, result.size)
    }
}