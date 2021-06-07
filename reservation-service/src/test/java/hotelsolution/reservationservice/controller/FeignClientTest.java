package hotelsolution.reservationservice.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    ids = "hotel-solution:hotel-service:+:stubs:8090")
public class FeignClientTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void given_WhenPassHotelName_ThenReturnHotelDetail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation/name/hotel1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.name").value("hotel1"))
        .andExpect(jsonPath("$.starRating").value("5"));
  }

  @Test
  public void given_WhenPassHotelId_ThenReturnAllRoom() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation/room/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", isA(ArrayList.class)))
        .andExpect(jsonPath("$.*", hasSize(2)))
        .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 4)))
        .andExpect(jsonPath("$[*].roomNumber", containsInAnyOrder("a12", "E54")))
        .andExpect(jsonPath("$[*].roomTypeId", containsInAnyOrder(1, 3)))
        .andExpect(jsonPath("$[*].hotelId", containsInAnyOrder(1, 1)));
  }

}