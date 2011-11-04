package br.com.plugitin.apontamentos

class FeriadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def importacaoService

    def index = {
        redirect(action: "list", params: params)
    }
	
	def importacao ={
	}
	
	def importar ={
		importacaoService.importarFeriados(request.getFile('planilhaFeriados')?.inputStream)
		redirect action:importacao
	}

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [feriadoInstanceList: Feriado.list(params), feriadoInstanceTotal: Feriado.count()]
    }

    def create = {
        def feriadoInstance = new Feriado()
        feriadoInstance.properties = params
        return [feriadoInstance: feriadoInstance]
    }

    def save = {
        def feriadoInstance = new Feriado(params)
        if (feriadoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'feriado.label', default: 'Feriado'), feriadoInstance.id])}"
            redirect(action: "show", id: feriadoInstance.id)
        }
        else {
            render(view: "create", model: [feriadoInstance: feriadoInstance])
        }
    }

    def show = {
        def feriadoInstance = Feriado.get(params.id)
        if (!feriadoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
            redirect(action: "list")
        }
        else {
            [feriadoInstance: feriadoInstance]
        }
    }

    def edit = {
        def feriadoInstance = Feriado.get(params.id)
        if (!feriadoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [feriadoInstance: feriadoInstance]
        }
    }

    def update = {
        def feriadoInstance = Feriado.get(params.id)
        if (feriadoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (feriadoInstance.version > version) {
                    
                    feriadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'feriado.label', default: 'Feriado')] as Object[], "Another user has updated this Feriado while you were editing")
                    render(view: "edit", model: [feriadoInstance: feriadoInstance])
                    return
                }
            }
            feriadoInstance.properties = params
            if (!feriadoInstance.hasErrors() && feriadoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'feriado.label', default: 'Feriado'), feriadoInstance.id])}"
                redirect(action: "show", id: feriadoInstance.id)
            }
            else {
                render(view: "edit", model: [feriadoInstance: feriadoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def feriadoInstance = Feriado.get(params.id)
        if (feriadoInstance) {
            try {
                feriadoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feriado.label', default: 'Feriado'), params.id])}"
            redirect(action: "list")
        }
    }
}
