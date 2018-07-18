package com.seebattleclient.sendingdatamode;

import com.google.gson.Gson;
import com.seebattleclient.gameobjectcoordinates.GameObjectCoordinates;

import java.util.ArrayList;
import java.util.List;

public class SendingCoordinatesHandler implements SendingDataHandler {
    private static final int COORDINATES_NUMBER = 2;
    private Gson gson;

    public SendingCoordinatesHandler() {
        gson = new Gson();
    }

    public String handle(String line) {
        line = line.trim();
        int gameObjectSize = getGameObjectSize(line);
        if (gameObjectSize == 0) {
            return null;
        }
        String[] coordinatesArray = getCoordinatesArray(line);
        List<List<String>> coordinates = getCoordinates(gameObjectSize, coordinatesArray);
        if (coordinates == null) {
            return null;
        }
        return jsonGameObjectCoordinates(gameObjectSize, coordinates);
    }

    private int getGameObjectSize(String line) {
        int gameObjectSize;
        try {
            gameObjectSize = Character.getNumericValue(line.charAt(0));
        } catch (NumberFormatException ex) {
            gameObjectSize = 0;
        }
        return gameObjectSize;
    }

    private String[] getCoordinatesArray(String line) {
        String coordinatesLine = line.substring(1).trim();
        return coordinatesLine.split(" ");
    }

    private List<List<String>> getCoordinates(int gameObjectSize, String[] coordinatesArray) {
        List<List<String>> coordinates = new ArrayList<>();
        for (int i = 0; i < coordinatesArray.length; i++) {
            String oneObjectCoordinatesString = coordinatesArray[i];
            List<String> oneObjectCoordinatesCouples =
                    getOneObjectCoordinatesCouples(oneObjectCoordinatesString, gameObjectSize);
            if (oneObjectCoordinatesCouples == null) {
                return null;
            }
            coordinates.add(oneObjectCoordinatesCouples);
        }
        return coordinates;
    }

    private List<String> getOneObjectCoordinatesCouples(String oneObjectCoordinatesString, int gameObjectSize) {
        List<String> oneObjectCoordinatesCouples = null;
        if (isEnoughCoordinatesCouplesInString(oneObjectCoordinatesString, gameObjectSize)) {
            oneObjectCoordinatesCouples = splitOneObjectCoordinatesStringIntoCoordinatesCouples(oneObjectCoordinatesString);
        }
        return oneObjectCoordinatesCouples;
    }

    private boolean isEnoughCoordinatesCouplesInString(String oneObjectCoordinatesString, int gameObjectSize) {
        if (oneObjectCoordinatesString.length() == gameObjectSize * COORDINATES_NUMBER) {
            return true;
        }
        return false;
    }

    private List<String> splitOneObjectCoordinatesStringIntoCoordinatesCouples(String oneObjectCoordinatesString) {
        List<String> oneObjectCoordinates = new ArrayList<>();
        int j = 0;
        while (j <= oneObjectCoordinatesString.length() - 1) {
            String coordinatesCouple = oneObjectCoordinatesString.substring(j, j + COORDINATES_NUMBER);
            oneObjectCoordinates.add(coordinatesCouple);
            j = j + COORDINATES_NUMBER;
        }
        return oneObjectCoordinates;
    }

    private String jsonGameObjectCoordinates(int gameObjectSize, List<List<String>> coordinates) {
        GameObjectCoordinates gameObjectCoordinates = new GameObjectCoordinates(gameObjectSize, coordinates);
        String jsonGameObjectCoordinates = gson.toJson(gameObjectCoordinates);
        return jsonGameObjectCoordinates;
    }
}
