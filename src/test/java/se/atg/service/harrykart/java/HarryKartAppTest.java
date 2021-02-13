package se.atg.service.harrykart.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.atg.service.harrykart.java.model.request.HarryKartRequest;
import se.atg.service.harrykart.java.model.response.HarryKartResponse;
import se.atg.service.harrykart.java.service.HarryKartService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("java-test")
public class HarryKartAppTest {

  private final static URI harryKartApp = URI.create("http://localhost:8181/java/api/play");
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
    assertEquals(response.getRankings().size(), 3);
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(0).getHorse());
    assertEquals(HERCULES_BOKO, response.getRankings().get(1).getHorse());
    assertEquals(CARGO_DOOR, response.getRankings().get(2).getHorse());
  }

  @Test
  void verifyTopThreeForInput1() throws IOException {
    HarryKartRequest harryKartRequest = getHarryKartRequest("src/test/resources/input_1.xml");

    HarryKartResponse response = harryKartService.play(harryKartRequest);
    assertNotNull(response);
    assertEquals(response.getRankings().size(), 3);
    assertEquals(WAIKIKI_SILVIO, response.getRankings().get(0).getHorse());
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(1).getHorse());
    assertEquals(HERCULES_BOKO, response.getRankings().get(2).getHorse());
  }

  @Test
  void verifyTopThreeForInput2() throws IOException {
    HarryKartRequest harryKartRequest = getHarryKartRequest("src/test/resources/input_2.xml");

    HarryKartResponse response = harryKartService.play(harryKartRequest);
    assertNotNull(response);
    assertEquals(response.getRankings().size(), 3);
    assertEquals(HERCULES_BOKO, response.getRankings().get(0).getHorse());
    assertEquals(TIME_TO_BE_LUCKY, response.getRankings().get(1).getHorse());
    assertEquals(WAIKIKI_SILVIO, response.getRankings().get(2).getHorse());
  }

  private HarryKartRequest getHarryKartRequest(String source) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(source);
    return xmlMapper()
        .readValue(fileInputStream, HarryKartRequest.class);
  }

  private XmlMapper xmlMapper() {
    SimpleFilterProvider filters = new SimpleFilterProvider();
    filters.setFailOnUnknownId(false);
    XmlMapper xmlMapper = new XmlMapper();
    return xmlMapper;
  }
}
