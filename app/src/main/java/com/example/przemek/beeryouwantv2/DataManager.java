package com.example.przemek.beeryouwantv2;

import com.example.przemek.beeryouwantv2.model.ALevel;

import java.util.List;

/**
 * Created by Przemek on 04.12.2016.
 */

public interface DataManager {
    ALevel getALevel( int idALevel );
    List<ALevel> getALevels();
    int saveALevel(ALevel aLevel);
    boolean deleteALevel( int idALevel);
    boolean updateALvel (ALevel aLevel);

}
