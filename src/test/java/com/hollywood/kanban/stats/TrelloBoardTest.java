package com.hollywood.kanban.stats;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class TrelloBoardTest {

	private KanbanStatsSource board;
	private String boardName = "AfD Sprint 16";
	private String applicationKey = "2923d35c74e4570b7c733b5000e9d171";
	private String applicationToken = "cda1d6aaf195d6c6f8269f26a4e390fdc57803716a13fdb2c787100bea3946cb"; //KanbanStats
	
	@Before
	public void setUp() throws Exception {
		board = new TrelloBoard(boardName, applicationKey, applicationToken, new RestTemplate());
	}

	@Test
	public void test1() {
		HashMap<String,Integer> stats = board.getStats();
	}

}
