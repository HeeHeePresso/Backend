package org.heeheepresso.gateway.event

import com.google.common.collect.ImmutableList
import org.springframework.stereotype.Service

@Service
class EventService {

    fun getEventFeatures(): List<String> {
        return ImmutableList.of(
                "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349"
        )
    }
}