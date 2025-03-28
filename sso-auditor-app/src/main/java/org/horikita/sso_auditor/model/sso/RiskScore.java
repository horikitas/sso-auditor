package org.horikita.sso_auditor.model.sso;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@RequiredArgsConstructor
public class RiskScore {

    private final String applicationId;

    private final List<String> messages = new ArrayList<>();

    private double score;

    public void addRiskScore(double score) {
        this.score += score;
    }

    public void addMessage(String riskMessage) {
        messages.add(riskMessage);
    }

}
