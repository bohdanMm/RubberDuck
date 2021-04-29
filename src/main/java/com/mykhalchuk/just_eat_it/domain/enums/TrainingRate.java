package com.mykhalchuk.just_eat_it.domain.enums;

public enum TrainingRate {

    NO_TRAINING(1.2),
    RARE(1.375),
    OFTEN(1.55),
    INTENSIVE(1.725),
    PROFESSIONAL(1.9);

    private Double influence;

    private TrainingRate(Double influence){
        this.influence = influence;
    }

    public Double getInfluence(){
        return influence;
    }
}
