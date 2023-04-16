package com.prok.optimusroute.configuration;

import com.graphhopper.GraphHopper;
import com.graphhopper.GraphHopperConfig;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FootFlagEncoder;
import com.graphhopper.routing.util.parsers.OSMMaxSpeedParser;
import com.graphhopper.routing.util.parsers.OSMMaxWidthParser;
import com.graphhopper.routing.util.parsers.OSMTollParser;
import com.prok.optimusroute.util.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GraphHopperConfiguration {

    // Подумать о хранении в БД
    private static final String MAP = "map/moscow.osm.pbf";
    private static final String CACHE = "cache";

    @Bean
    public GraphHopper graphHopper(GraphHopperConfig config) {
        return new GraphHopperOSM()
                .init(config)
                .setEncodingManager(encodingManager())
                .importOrLoad();
    }

    @Bean("carProfile")
    public Profile carProfile() {
        Profile profile = new Profile("car");
        profile.setVehicle("car");
        profile.setWeighting("fastest");
        return profile;
    }

    @Bean("footProfile")
    public Profile footProfile() {
        Profile profile = new Profile("foot");
        profile.setVehicle("foot");
        profile.setWeighting("fastest");
        return profile;
    }

    @Bean
    public GraphHopperConfig graphHopperConfig(List<Profile> profiles) {
        GraphHopperConfig config = new GraphHopperConfig();
        config.putObject("datareader.file", FileUtils.getResourceFilePath(MAP));
        config.putObject("graph.location", CACHE);
        config.setProfiles(profiles);
        return config;
    }

    @Bean
    public EncodingManager encodingManager() {
        return new EncodingManager.Builder()
                .setEnableInstructions(true)
                .add(new OSMMaxSpeedParser())
                .add(new OSMMaxWidthParser())
                .add(new OSMTollParser())
                .add(new FootFlagEncoder())
                .add(new CarFlagEncoder())
                .build();
    }

}
