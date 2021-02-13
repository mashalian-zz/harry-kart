package se.atg.service.harrykart.java.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import se.atg.service.harrykart.java.model.request.HarryKartRequest;
import se.atg.service.harrykart.java.model.request.Loop;
import se.atg.service.harrykart.java.model.request.Participant;
import se.atg.service.harrykart.java.model.response.HarryCartPlacement;
import se.atg.service.harrykart.java.model.response.HarryKartResponse;
import se.atg.service.harrykart.java.util.HarryKartMapper;

@Service
public class HarryKartService {


  private static final int LOOP_LENGTH = 1000;

  public HarryKartResponse play(HarryKartRequest request) {
    List<HarryCartPlacement> topThreeHorses = findTopThreeHorses(request);

    return HarryKartResponse.builder()
        .rankings(topThreeHorses)
        .build();
  }

  private List<HarryCartPlacement> findTopThreeHorses(HarryKartRequest request) {

    List<Participant> winners = request.getStartList()
        .stream()
        .filter(Objects::nonNull)
        .map(participant -> calculateTotalTimeForEachParticipant(participant, request.getPowerUps()))
        .sorted(Comparator.comparingDouble(Participant::getTimeTakenForAllLoops))
        .limit(3)
        .collect(Collectors.toList());

    return HarryKartMapper.mapParticipantToResponse(winners);
  }


  private Participant calculateTotalTimeForEachParticipant(Participant participant, List<Loop> powersUps) {
    int participantLane = participant.getLane();
    //First loop
    participant.setTimeTakenForAllLoops(getTimeForEachLoop(participant.getBaseSpeed()));

    powersUps.stream()
        .filter(Objects::nonNull)
        .forEach(loop -> {
          int powerValue = loop.getLanes().get(participantLane - 1).getPowerValue();
          double timeForThisLoop = getTimeForEachLoop(powerValue + participant.getBaseSpeed());
          double v =  participant.getTimeTakenForAllLoops() + timeForThisLoop;
          participant.setTimeTakenForAllLoops((v));
        });

    return participant;
  }

  private double getTimeForEachLoop(int speed) {
    return (double) LOOP_LENGTH / (double) speed;
  }
}
