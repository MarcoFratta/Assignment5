package com.escooter.presentation_layer;

public record NewRideMessage(String userId, String eScooterId) {

    @Override
    public String toString() {
        return "NewRideMessage{" +
                "userId='" + userId + '\'' +
                ", eScooterId='" + eScooterId + '\'' +
                '}';
    }
}
