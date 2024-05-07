package com.darfik.skycast.location;

import com.darfik.skycast.commons.services.JsonParser;
import com.darfik.skycast.user.UserDAO;
import com.darfik.skycast.user.UserDTO;
import com.darfik.skycast.usersession.UserSessionDTO;
import com.darfik.skycast.weather.WeatherJson;
import com.darfik.skycast.weather.WeatherJsonParser;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class LocationService {
    private final LocationDAO locationDAO;
    private final UserDAO userDAO;
    private final OpenWeatherAPIService openWeatherAPIService;
    private final JsonParser<LocationJson> locationParser = new LocationJsonParser();
    private final JsonParser<WeatherJson> weatherParser = new WeatherJsonParser();

    LocationService() {
        locationDAO = LocationDAO.getInstance();
        openWeatherAPIService = new OpenWeatherAPIService();
        userDAO = UserDAO.getInstance();
    }


    //TODO Usersession?
    public void addLocationForUser(LocationDTO locationDTO, UserDTO userDTO) throws IOException, URISyntaxException, InterruptedException {
        if (!locationExistsForUser(locationDTO, userDTO)) {
            Location location = LocationMapper.toModel(getLocationByName(locationDTO));
            location.setUser(userDAO.find(userDTO.getUsername()).get());
            locationDAO.save(location);
        } else {
            throw new IllegalArgumentException("This location is already added");
        }
    }

    public List<LocationDTO> getUserLocations(UserDTO userDTO) {
        return locationDAO.findLocationsByUser(userDAO.find(userDTO.getUsername()).get())
                .stream().map(LocationMapper::toDTO)
                .collect(Collectors.toList());
    }

    private boolean locationExistsForUser(LocationDTO locationDTO, UserDTO userDTO) {
        List<LocationDTO> userLocations = getUserLocations(userDTO);
        return userLocations.contains(locationDTO);
    }

    public LocationDTO getLocationByName(LocationDTO locationDTO) throws IOException, URISyntaxException, InterruptedException {
            LocationJson parsedJson = locationParser.parse(openWeatherAPIService.getLocationByName(locationDTO.getName()));
            locationDTO.setLatitude(parsedJson.getLatitude());
            locationDTO.setLongitude(parsedJson.getLongitude());
        return locationDTO;
    }

    public LocationDTO getWeatherByCoordinates(LocationDTO locationDTO) {
        try {
            WeatherJson parsedWeatherJson = weatherParser.parse(openWeatherAPIService.getWeatherByCoordinates(locationDTO));
            locationDTO.setTemperature(parsedWeatherJson.getMainWeatherData().getTemp());
            return locationDTO;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            log.error("//TODO");
        }
        return null;
    }
}
