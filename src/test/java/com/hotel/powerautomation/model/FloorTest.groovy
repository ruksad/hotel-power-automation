package com.hotel.powerautomation.model

import spock.lang.Specification

class FloorTest extends Specification {
    def floor=new Floor();
    def "CalculatePowerConsumption"() {
        given:
        when:
        def ans=floor.calculatePowerConsumption(10,10)
        then:
        ans==250
    }
}
