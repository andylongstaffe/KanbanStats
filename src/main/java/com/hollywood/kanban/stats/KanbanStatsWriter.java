package com.hollywood.kanban.stats;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hollywood.kanban.stats.binding.KanbanStats;

public class KanbanStatsWriter {

	public void write(KanbanStats stats, File outputFile) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
		try {
			writer.writeValue(outputFile, stats);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
