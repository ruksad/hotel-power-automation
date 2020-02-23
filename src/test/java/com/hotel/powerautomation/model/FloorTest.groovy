package com.hotel.powerautomation.model

import spock.lang.Specification

class FloorTest extends Specification {
    def "CalculatePowerConsumption"() {
        given:
        when:
        def ans=Floor.calculatePowerConsumption(10,10)
        then:
        ans==250
    }
}
