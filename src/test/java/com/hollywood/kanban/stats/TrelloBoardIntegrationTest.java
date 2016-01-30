package com.hollywood.kanban.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.hollywood.kanban.trello.binding.TrelloCard;
import com.hollywood.kanban.trello.binding.TrelloList;

public class TrelloBoardIntegrationTest {

	private TrelloBoard board;
	private String boardName = "AfD Sprint 16";
	private String applicationKey = "2923d35c74e4570b7c733b5000e9d171";
	private String applicationToken = "cda1d6aaf195d6c6f8269f26a4e390fdc57803716a13fdb2c787100bea3946cb"; //KanbanStats
	
	@Before
	public void setUp() throws Exception {
		board = new TrelloBoard(boardName, applicationKey, applicationToken, new RestTemplate());
	}

	@Test
	public void canConnectToBoard() throws Exception {
		
	}
	
	@Test
	public void printCards() throws Exception {
		List<TrelloCard> cards = board.getCards();
		for ( TrelloCard card : cards ) {
			System.out.println(card);
		}
	}
	
	@Test
	public void printLists() throws Exception {
		List<TrelloList> lists = board.getLists();
		for ( TrelloList list : lists) {
			System.out.println(list);
		}
	}




}
