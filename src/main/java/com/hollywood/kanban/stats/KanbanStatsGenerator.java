package com.hollywood.kanban.stats;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hollywood.kanban.stats.binding.KanbanStats;
import com.hollywood.kanban.trello.binding.TrelloCard;
import com.hollywood.kanban.trello.binding.TrelloList;

public class KanbanStatsGenerator {

	private TrelloBoard trelloBoard;
		
	public KanbanStatsGenerator(TrelloBoard trelloBoard) {
		this.trelloBoard = trelloBoard;
	}

	public KanbanStats getStats() {
		List<TrelloList> lists = trelloBoard.getLists();
		List<TrelloCard> cards = trelloBoard.getCards();
		Map<String,Integer> columnIdCounts = new HashMap<>();
		for ( TrelloCard card : cards) {
			String listId = card.getIdList();
			Integer count = columnIdCounts.get(listId);
			if (count == null) {
				columnIdCounts.put(card.getIdList(), 1);
			}
			else {
				columnIdCounts.put(listId, count+1);
			}
		}
		// Swaps ids for column names
		Map<String,Integer> results = new HashMap<>();
		for ( String id : columnIdCounts.keySet() ) {
			for ( TrelloList list : lists ) {
				if ( list.getId().equals(id) ) {
					results.put(list.getName(), columnIdCounts.get(id));
					break;
				}
			}
		}
		return new KanbanStats(results, new Date(), trelloBoard.getBoardName());
	}

	
	
}
