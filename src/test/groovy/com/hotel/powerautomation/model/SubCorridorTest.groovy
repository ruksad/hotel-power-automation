package com.hotel.powerautomation.model

import spock.lang.Specification

class SubCorridorTest extends Specification {

    def "createCorridor"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=SubCorridor.createCorridor(5);
        then:
        list.size()>0;
        list.get(0).getDevices().get(0).stateToString()=="OFF"

    }
}
