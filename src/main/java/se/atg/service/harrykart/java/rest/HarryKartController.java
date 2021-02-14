package se.atg.service.harrykart.java.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.model.request.HarryKartRequest;
import se.atg.service.harrykart.java.model.response.HarryKartResponse;
import se.atg.service.harrykart.java.service.HarryKartService;

@RestController
@RequestMapping("/java/api")
@Slf4j
public class HarryKartController {

  private final HarryKartService harryKartService;

  public HarryKartController(HarryKartService harryKartService) {
    this.harryKartService = harryKartService;
  }

  @PostMapping(path = "/play",
      consumes = MediaType.APPLICATION_XML_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public HarryKartResponse playHarryKart(@RequestBody HarryKartRequest request) {
    log.info("Got request {}", request);
    return harryKartService.play(request);
  }

}
