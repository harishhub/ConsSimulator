package com.example.service.rest;

public enum ActionType {

    CREATED,
    UPDATED,
    DELETED,
    UNDELETED;

    public static ActionType fromString(String actionType){
        for(ActionType action: ActionType.values()){
            if(action.name().equalsIgnoreCase(actionType))
                return action;
        }
        return null;
    }
}
