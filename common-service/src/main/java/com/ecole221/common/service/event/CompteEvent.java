package com.ecole221.common.service.event;

import com.ecole221.common.service.dto.CompteDTO;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CompteEvent implements Event{
    private CompteStatus compteStatus;
    private CompteDTO compteDTO;

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    public CompteEvent(){}

    public CompteEvent(CompteStatus compteStatus, CompteDTO compteDTO, UUID eventId, Date eventDate) {
        this.compteStatus = compteStatus;
        this.compteDTO = compteDTO;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }

    public CompteEvent(CompteStatus compteStatus, CompteDTO compteDTO) {
        this.compteStatus = compteStatus;
        this.compteDTO = compteDTO;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEventDate() {
        return eventDate;
    }
}
