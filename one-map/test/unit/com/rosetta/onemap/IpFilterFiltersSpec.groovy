package com.rosetta.onemap

import com.rosetta.onemap.IpFilters;

import grails.test.mixin.Mock
import spock.lang.Specification

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;


@Mock(IpFilters)
class IpFilterFiltersSpec extends Specification {
	private static final Log log = LogFactory.getLog(this)
	
    def setup() {
    }

    def cleanup() {
    }

    void "test logging"() {
		when:
		log.info "test logging from within IpFilterFiltersSpec. Go Bucks!"
		then:
		true
    }
}
