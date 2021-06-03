package contracts


import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Contract for API endpoint /api/hotel/name/{hotelName}"
    request {
        method GET()
        url("/api/hotel/name/hotel1")
    }
    response {
        status 200
        body(
                id: "1",
                name: "hotel1",
                starRating: "5"
        )
        headers {
            contentType(applicationJson())
        }
    }
}