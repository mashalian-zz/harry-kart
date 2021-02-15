package se.atg.service.harrykart.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.atg.service.harrykart.java.model.request.HarryKartRequest;
import se.atg.service.harrykart.java.model.response.HarryKartResponse;
import se.atg.service.harrykart.java.service.HarryKartService;

@ExtendWith(SpringExtension.class)
public class HarryKartServiceTest {

  private final static String TIME_TO_BE_LUCKY = "TIMETOBELUCKY";
  private final static String CARGO_DOOR = "CARGO DOOR";
  private final static String HERCULES_BOKO = "HERCULES BOKO";
  private final static String WAIKIKI_SILVIO = "WAIKIKI SILVIO";

  private HarryKartService harryKartService;

  @BeforeEach
  void initialize() {
    harryKartService = new HarryKartService();
  }

  @Test
  void verifyTopThreeForInput0() throws IOException {
    HarryKartRequest harryKartRequest = getHarryKartRequest("src/test/resources/input_0.xml");

    HarryKartResponse response = harryKartService.play(harryKartRequest);
    assertNotNull(response);
    assertEquals(3, response.getRankings().size());
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(0).getHorse());
    assertEquals(1, response.getRankings().get(0).getPosition());
    assertEquals(HERCULES_BOKO, response.getRankings().get(1).getHorse());
    assertEquals(2, response.getRankings().get(1).getPosition());
    assertEquals(CARGO_DOOR, response.getRankings().get(2).getHorse());
    assertEquals(3, response.getRankings().get(2).getPosition());
  }

  @Test
  void verifyTopThreeForInput1() throws IOException {
    HarryKartRequest harryKartRequest = getHarryKartRequest("src/test/resources/input_1.xml");

    HarryKartResponse response = harryKartService.play(harryKartRequest);
    assertNotNull(response);
    assertEquals(3, response.getRankings().size());
    assertEquals(WAIKIKI_SILVIO, response.getRankings().get(0).getHorse());
    assertEquals(1, response.getRankings().get(0).getPosition());
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(1).getHorse());
    assertEquals(2, response.getRankings().get(1).getPosition());
    assertEquals(HERCULES_BOKO, response.getRankings().get(2).getHorse());
    assertEquals(3, response.getRankings().get(2).getPosition());
  }

  @Test
  void verifyTopThreeForInput2() throws IOException {
    HarryKartRequest harryKartRequest = getHarryKartRequest("src/test/resources/input_2.xml");

    HarryKartResponse response = harryKartService.play(harryKartRequest);
    assertNotNull(response);
    assertEquals(3, response.getRankings().size());
    assertEquals(HERCULES_BOKO, response.getRankings().get(0).getHorse());
    assertEquals(1, response.getRankings().get(0).getPosition());
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(1).getHorse());
    assertEquals(2, response.getRankings().get(1).getPosition());
    assertEquals(WAIKIKI_SILVIO, response.getRankings().get(2).getHorse());
    assertEquals(3, response.getRankings().get(2).getPosition());
  }

  private HarryKartRequest getHarryKartRequest(String source) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(source);
    return new XmlMapper()
        .readValue(fileInputStream, HarryKartRequest.class);
  }
}
