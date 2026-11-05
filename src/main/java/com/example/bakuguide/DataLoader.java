package com.example.bakuguide;

import com.example.bakuguide.model.Landmark;
import com.example.bakuguide.repository.LandmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final LandmarkRepository repository;

    public DataLoader(LandmarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        repository.saveAll(List.of(
            new Landmark("Icherisheher (Old City)", "Historic",
                "The walled medieval core of Baku and a UNESCO World Heritage Site. Wander narrow "
                + "limestone lanes, caravanserais, hammams and the 12th-century Palace of the Shirvanshahs.",
                "Old City, Baku", "/images/icherisheher.jpg"),
            new Landmark("Flame Towers", "Modern Architecture",
                "Three flame-shaped skyscrapers that dominate the skyline. At night their LED facades "
                + "show flickering flames, waving flags and dazzling animations.",
                "Mehdi Huseyn St, Baku", "/images/flame-towers.jpg"),
            new Landmark("Heydar Aliyev Center", "Modern Architecture",
                "A flowing, curvilinear cultural center designed by Zaha Hadid. Its seamless white "
                + "waves house exhibitions, a museum and a concert hall.",
                "Heydar Aliyev Ave, Baku", "/images/heydar-aliyev-center.jpg"),
            new Landmark("Baku Boulevard", "Park & Promenade",
                "A seaside promenade stretching for kilometers along the Caspian Sea, with gardens, "
                + "cafes, a carousel, Little Venice canals and sweeping bay views.",
                "Neftchilar Ave, Baku", "/images/baku-boulevard.jpg"),
            new Landmark("Maiden Tower", "Historic",
                "A mysterious 12th-century stone tower rising from the Old City. Climb its eight "
                + "levels for panoramic views over rooftops and the Caspian.",
                "Gala Street, Old City, Baku", "/images/maiden-tower.jpg")
        ));
    }
}
