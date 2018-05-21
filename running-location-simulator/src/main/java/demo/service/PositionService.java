package demo.service;

import demo.model.CurrentPosition;

/**
 * Created by hectorlueng on 5/12/18.
 */
public interface PositionService {

    void processPositionInfo(long id, CurrentPosition currentPosition, boolean sendPositionToDistributionService);
}
