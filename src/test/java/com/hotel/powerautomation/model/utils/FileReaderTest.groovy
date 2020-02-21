package com.hotel.powerautomation.model.utils

import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class FileReaderTest extends Specification {
    def fileReader=new FileReader();
    def "Read"() {
        given:
        def filepath=new ClassPathResource("input.txt");
        when:
        def input=fileReader.read(filepath.getURL().file)
        then:
        input.noOfFloors>0

    }

    def "ReadLine"() {
        given:
        def list=Arrays.asList("number of floors: 2",
                       "main corridors per floor: 1",
                       "sub corridors per floor: 2",
                       "movement in Floor 1, Sub corridor 2",
                       "no movement in Floor 1, Sub corridor 2 for 1 minute")
        when:
        def input = fileReader.readLine(list)
        then:
        input.moves.get(0).movement
    }

    def "GetDigitsOfString"() {
        given:
        when:
        def digits=fileReader.getDigitsOfString("No movement in Floor 1, Sub corridor 2 for 1 minute")
        then:
        digits.matches("[0-9]*")
    }

    def "ReadMovement"() {
    }
}
