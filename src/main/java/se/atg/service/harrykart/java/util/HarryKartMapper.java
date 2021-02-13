package se.atg.service.harrykart.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.atg.service.harrykart.java.model.request.Participant;
import se.atg.service.harrykart.java.model.response.HarryCartPlacement;

public class HarryKartMapper {

  public static List<HarryCartPlacement> mapParticipantToResponse(List<Participant> winners) {

    List<HarryCartPlacement> harryCartPlacements = new ArrayList<>();
    for (int i = 0; i < winners.size(); i++) {
      Participant participant = winners.get(i);
      harryCartPlacements.add(HarryCartPlacement.builder()
          .horse(participant.getName())
          .position(i + 1)
          .totalTimeTaken(participant.getTimeTakenForAllLoops())
          .build());
    }
    return Collections.unmodifiableList(harryCartPlacements);

  }

}
