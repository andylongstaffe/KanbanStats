package com.hollywood.kanban.stats;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

public class TrelloBoard implements KanbanStatsSource {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrelloBoard.class);
	
    public static final String API_URL = "https://api.trello.com/1";
    public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";
	
	private String boardName;
	private String applicationKey;
	private String applicationToken;
	private RestTemplate restTemplate;	

	public TrelloBoard(String boardName, String applicationKey, String applicationToken, RestTemplate restTemplate) {
		this.boardName = boardName;
		this.applicationKey = applicationKey;
		this.applicationToken = applicationToken;
		this.restTemplate = restTemplate;
	}
	
	private void retrieveStats() {
		String boardId = getBoardId();
		LOG.debug("Board id=" + boardId);
		HashMap<String, Integer> columnCounts = getColumnCounts(boardId);
	}

	private HashMap<String, Integer> getColumnCounts(String boardId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getURL(String restPath) {
		return String.format(API_URL + "/" + restPath + "?" + API_KEY_TOKEN_PARAM);
	}

	private String getBoardId() {
		String boardId = null;
		String url = getURL("member/me/boards");
		String response = restTemplate.getForObject(url, String.class, applicationKey, applicationToken);
		System.out.println(response);
		List<String> result = JsonPath.read(response, "$..*[?(@.name=='" + boardName + "')].id");
		if ( result.size() == 1) {
			boardId = result.get(0);
		}
		return boardId;
	}

	@Override
	public HashMap<String,Integer> getStats() {
		retrieveStats();
		return null;
	}

}
