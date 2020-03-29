package org.levelup.job.list.Jdbc;

import org.levelup.job.list.Domain.Position;

import java.sql.SQLException;
import java.util.Collection;

public interface PositionService {
    Position createPosition(String name) throws SQLException;
    void deletePositionById(int id);
    void deletePositionByName(String name);
    Collection<Position> findAllPositionWhichNameLike(String name);
    Position findPositionById(int id);
    Collection <Position> findAllPositions();
    Position findPositionByName(String name);
}
