package com.prok.optimusroute.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class AnswerWrap {
    private List<RoutePart> answers;
    private static final Long TIME_SPREAD = 600L;
    private LocalDateTime bestTime;

    public void add(RoutePart routePart) {
        LocalDateTime calcTime = routePart.getCalcDateTime();
        if (answers.size() < 3) {
            answers.add(routePart);
            if (bestTime == null || calcTime.isBefore(bestTime)) {
                bestTime = calcTime;
            }
            return;
        }
        if (calcTime.isBefore(bestTime)) {
            bestTime = calcTime;
            RoutePart oldRoutePart = answers.stream().max(Comparator.comparing(RoutePart::getTime)).orElseThrow();
            answers.remove(oldRoutePart);
            answers.add(routePart);
            return;
        }
        if (calcTime.isBefore(bestTime.plusSeconds(TIME_SPREAD))) {
            RoutePart oldRoutePart = answers.stream().max(Comparator.comparing(RoutePart::getTime)).orElseThrow();
            if (calcTime.isBefore(oldRoutePart.getCalcDateTime())) {
                answers.remove(oldRoutePart);
                answers.add(routePart);
            }
        }
    }

    public boolean isActual(LocalDateTime date) {
        return bestTime == null || date.isBefore(bestTime.plusSeconds(TIME_SPREAD));
    }

    public static AnswerWrap create() {
        return new AnswerWrap()
                .setAnswers(new ArrayList<>());
    }
}
