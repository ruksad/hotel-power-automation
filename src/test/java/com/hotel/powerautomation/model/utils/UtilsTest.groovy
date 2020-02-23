package com.hotel.powerautomation.model.utils

import spock.lang.Specification

class UtilsTest extends Specification {

    def "GetDigitsOfString"() {
        given:
        when:
        def digits=Utils.getDigitsOfString("No movement in Floor 1, Sub corridor 2 for 1 minute")
        then:
        digits.matches("[0-9]*")
    }
}
