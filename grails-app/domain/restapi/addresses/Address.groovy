package restapi.addresses

class Address {

    String line1
    String line2
    String city
    String state
    String zip

    static constraints = {
        line2 nullabe: true
    }
}
