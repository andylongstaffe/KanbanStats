package com.hollywood.kanban.stats;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.hollywood.kanban.stats.binding.KanbanStats;

public class KanbanStatsIntegrationTest {
	
	
	@Test
	public void printColumnCounts() throws Exception {
		String boardName = "AfD Sprint 16";
		String applicationKey = "2923d35c74e4570b7c733b5000e9d171";
		String applicationToken = "cda1d6aaf195d6c6f8269f26a4e390fdc57803716a13fdb2c787100bea3946cb"; 

		TrelloBoard board = new TrelloBoard(boardName, applicationKey, applicationToken, new RestTemplate());
		KanbanStatsGenerator generator = new KanbanStatsGenerator(board);
		KanbanStats stats = generator.getStats();
		
		Map<String, Integer> columnCounts = stats.getColumnCounts();
		for ( String name : columnCounts.keySet()) {
			System.out.println(name + " "  + columnCounts.get(name));
		}
		
		KanbanStatsWriter writer = new KanbanStatsWriter();
		writer.write(new KanbanStats(columnCounts, new Date(), "test"), new File("test-output/print-column-counts-test.json"));
	}

}
