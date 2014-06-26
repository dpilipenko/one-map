package com.rosetta.onemap



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HotspotController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Hotspot.list(params), model:[hotspotInstanceCount: Hotspot.count()]
    }

    def show(Hotspot hotspotInstance) {
        respond hotspotInstance
    }

    def create() {
        respond new Hotspot(params)
    }

    @Transactional
    def save(Hotspot hotspotInstance) {
        if (hotspotInstance == null) {
            notFound()
            return
        }

        if (hotspotInstance.hasErrors()) {
            respond hotspotInstance.errors, view:'create'
            return
        }

        hotspotInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotspotInstance.label', default: 'Hotspot'), hotspotInstance.id])
                redirect hotspotInstance
            }
            '*' { respond hotspotInstance, [status: CREATED] }
        }
    }

    def edit(Hotspot hotspotInstance) {
        respond hotspotInstance
    }

    @Transactional
    def update(Hotspot hotspotInstance) {
        if (hotspotInstance == null) {
            notFound()
            return
        }

        if (hotspotInstance.hasErrors()) {
            respond hotspotInstance.errors, view:'edit'
            return
        }

        hotspotInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Hotspot.label', default: 'Hotspot'), hotspotInstance.id])
                redirect hotspotInstance
            }
            '*'{ respond hotspotInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Hotspot hotspotInstance) {

        if (hotspotInstance == null) {
            notFound()
            return
        }

        hotspotInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Hotspot.label', default: 'Hotspot'), hotspotInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotspotInstance.label', default: 'Hotspot'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
