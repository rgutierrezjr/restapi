package restapi.products

import grails.rest.RestfulController

class ProductController extends RestfulController {
	static responseFormats = ['json', 'xml']

    ProductsService productsService

    ProductController(Product) {
        super(Product)
    }

    def index() { }
}
