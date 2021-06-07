package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Contract for API endpoint /api/hotel/room/{hotelId}"
    request {
        method GET()
        url("/api/hotel/room/1")
    }
    response {
        status 200
        body([
                [
                        "id"        : "1",
                        "roomNumber": "a12",
                        "roomTypeId": "1",
                        "hotelId"   : "1"],
                [
                        "id"        : "4",
                        "roomNumber": "E54",
                        "roomTypeId": "3",
                        "hotelId"   : "1"]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}