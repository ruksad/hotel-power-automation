package com.hotel.powerautomation.model.input

import spock.lang.Specification

class InPutTest extends Specification {
    def "ReadLine"() {
        given:
        def list=Arrays.asList("number of floors: 2",
                "main corridors per floor: 1",
                "sub corridors per floor: 2",
                "movement in Floor 1, Sub corridor 2",
                "no movement in Floor 1, Sub corridor 2 for 1 minute")
        when:
        def input = Input.readLine(list)
        then:
        input.moves.get(0).movement
    }
}
