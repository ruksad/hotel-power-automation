package com.hotel.powerautomation.model.abstractmodels

import spock.lang.Specification

class DeviceTest extends Specification {

    def "getDevice"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=Device.getDevice();
        then:
        list.size()>0;
        list.get(0).state()

    }
}
