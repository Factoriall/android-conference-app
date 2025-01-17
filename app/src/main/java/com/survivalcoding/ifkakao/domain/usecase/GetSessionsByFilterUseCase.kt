package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetSessionsByFilterUseCase(
    private val getSessionsByDayUseCase: GetSessionsByDayUseCase
) {
    suspend operator fun invoke(
        day: Int,
        fields: List<String>,
        classification: List<String>,
        techClassification: List<String>,
        companies: List<String>
    ): List<Session> =
        getSessionsByDayUseCase.invoke(day).filter {
            isInSameClassification(it.field, fields)
        }.filter {
            isInSameClassification(it.relationList.CLASSIFICATION, classification)
        }.filter {
            isInSameClassification(it.relationList.TECH_CLASSIFICATION, techClassification)
        }.filter {
            isInSameClassification(it.company, companies)
        }

    private fun isInSameClassification(
        subject: String,
        compareList: List<String>
    ): Boolean {
        if (compareList.isEmpty()) return true
        if (subject in compareList) return true
        return false
    }

    private fun isInSameClassification(
        subjects: List<String>,
        compareList: List<String>
    ): Boolean {
        if (compareList.isEmpty()) return true
        for (s in subjects) if (s in compareList) return true
        return false
    }
}