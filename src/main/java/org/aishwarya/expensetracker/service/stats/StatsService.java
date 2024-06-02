package org.aishwarya.expensetracker.service.stats;

import org.aishwarya.expensetracker.dto.GraphDTO;
import org.aishwarya.expensetracker.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();
    StatsDTO getStats();

}
