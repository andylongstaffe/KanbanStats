package com.hollywood.kanban.stats;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.hollywood.kanban.stats.binding.KanbanStats;

public class KanbanRunner {

	public static void main(String[] args) {
		String boardName = "AfD Sprint 16";
		String applicationKey = "2923d35c74e4570b7c733b5000e9d171";
		String applicationToken = "cda1d6aaf195d6c6f8269f26a4e390fdc57803716a13fdb2c787100bea3946cb"; 

		TrelloBoard board = new TrelloBoard(boardName, applicationKey, applicationToken, new RestTemplate());
		KanbanStatsGenerator generator = new KanbanStatsGenerator(board);
		KanbanStats stats = generator.getStats();
		
		new KanbanStatsWriter().write(stats, new File("kanban-runner-stats.json"));

	}

}
