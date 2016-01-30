package com.hollywood.kanban.stats;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hollywood.kanban.stats.binding.KanbanStats;

public class KanbanStatsWriterTest {
	
	@Test
	public void writeSomeStats() throws Exception {
		File file = new File("test-output");
		file.mkdir();
		
		KanbanStatsWriter writer = new KanbanStatsWriter();
		File outputFile = new File("test-output/stats.json");
		Map<String, Integer> columnCounts = new HashMap<String,Integer>();
		columnCounts.put("column1", 1);
		columnCounts.put("column2", 2);
		writer.write(new KanbanStats(columnCounts, new Date(), "test"), outputFile);
	}

}
