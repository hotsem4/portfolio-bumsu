package com.choebum.portfolio.presentation.service

import com.choebum.portfolio.domain.entity.Introduction
import com.choebum.portfolio.domain.entity.Link
import com.choebum.portfolio.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {
    @InjectMocks
    lateinit var presentationService: PresentationService

    @Mock
    lateinit var presentationRepository: PresentationRepository

    val DATA_SIZE = 9

    @Test
    fun testGetIntroductions() {
        // given -- 조건이 주어지고
        val introductions = mutableListOf<Introduction>()
        // 빈 리스트 형태의 introductions 생성
        for (i in 1..DATA_SIZE) {
            val introduction = Introduction(content = "${i}", isActive = i % 2 == 0)
            // Introduction 객체를 생성할껀데 content에는 i를 넣고 isActive에는 홀짝으로 해서 짝에 true, 홀에 false가 들어가게 한다.
            introductions.add(introduction)
            // 만든 객체 introduction을 빈 배열인 introductions에 삽입한다.
        }
        val activeIntroductions = introductions.filter { introduction -> introduction.isActive }
        // introductions에서 활성화된 친구를 뽑아서 activeIntroductions에 넣는다.
        Mockito.`when`(presentationRepository.getActiveIntroductions())
            // 가짜 객체를 만들껀데 presentationRepository.getActiveIntroductions가 실행되면 -> activeIntroductions를 반환해라.
            .thenReturn(activeIntroductions)
        // when  -- 동작을 했을 때
        val introductionDTOs = presentationService.getIntroductions()

        //then  -- 어떤 결과가 나올 것인가?
        assertThat(introductionDTOs).hasSize(DATA_SIZE / 2)
        for (introductionDTO in introductionDTOs) {
            assertThat(introductionDTO.content.toInt()).isEven()
        }
    }

    @Test
    fun testGetLinks() {
        // given

        val links = mutableListOf<Link>()
        for (i in 1..DATA_SIZE) {
            val link = Link(name = "${i}", content = "${i}", isActive = i % 2 != 0)
            links.add(link)
        }

        val activeLinks = links.filter { link -> link.isActive }
        Mockito.`when`(presentationRepository.getActiveLinks()).thenReturn(activeLinks)

        // when
        val linkDTOs = presentationService.getLinks()

        //then
//        assertThat(linksDTOs).hasSize((DATA_SIZE / 2) + 1)
//        for (linkDTO in linksDTOs) {
//            assertThat(linkDTO.content.toInt()).isEven()
//        }
        var expectedSize = DATA_SIZE / 2 // 9 / 2 = 4.5 -> 4
        // 만약 DATA_SIZE = 9라면 /2 했을 때 4.5가 나오는데 이럴 경우 버림을 해서 4만 나오게 된다.
        if (DATA_SIZE % 2 != 0) {
            expectedSize += 1
        }
        assertThat(linkDTOs).hasSize(expectedSize)
        for (linkDTO in linkDTOs) {
            assertThat(linkDTO.content.toInt()).isOdd()
        }
    }
}