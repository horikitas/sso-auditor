package org.horikita.sso_auditor.mapper;

import org.horikita.sso_auditor.model.sso.RiskScore;
import org.horikita.sso_auditor_api.model.AuditResult;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Clock;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface AuditResultMapper {
    @Mapping(target = "auditMessages", source = "dto.messages")
    @Mapping(target = "riskScore", source = "dto.score")
    //@Mapping(target = "auditDate", expression = "java(OffsetDateTime.now(clock))")
    AuditResult apply(RiskScore dto, Clock clock);

    @AfterMapping
    default void mapAuditDate(@MappingTarget AuditResult target, Clock clock) {
        target.auditDate(OffsetDateTime.now(clock));
    }
}
