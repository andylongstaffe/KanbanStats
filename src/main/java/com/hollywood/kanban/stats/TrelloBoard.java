package com.hollywood.kanban.stats;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollywood.kanban.trello.binding.TrelloCard;
import com.hollywood.kanban.trello.binding.TrelloList;
import com.jayway.jsonpath.JsonPath;

/**
 * 
 * Useful resource: https://developers.trello.com/sandbox
 * 
 * @author andylongstaffe
 *
 */
public class TrelloBoard {

	private static final Logger LOG = LoggerFactory.getLogger(TrelloBoard.class);

	public static final String API_URL = "https://api.trello.com/1";
	public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";

	private String boardName;
	private String applicationKey;
	private String applicationToken;
	private RestTemplate restTemplate;
	private String boardId = null;

	public TrelloBoard(String boardName, String applicationKey, String applicationToken, RestTemplate restTemplate) {
		this.boardName = boardName;
		this.applicationKey = applicationKey;
		this.applicationToken = applicationToken;
		this.restTemplate = restTemplate;
	}

	public List<TrelloCard> getCards() {
		String response = trelloGetRequest("board/" + getBoardId() + "/cards");
		ObjectMapper mapper = new ObjectMapper();
		List<TrelloCard> cards = null;
		try {
			cards = mapper.readValue(response, new TypeReference<List<TrelloCard>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public List<TrelloList> getLists() {
		String response = trelloGetRequest("board/" + getBoardId() + "/lists");
		ObjectMapper mapper = new ObjectMapper();
		List<TrelloList> lists = null;
		try {
			lists = mapper.readValue(response, new TypeReference<List<TrelloList>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lists;
	}

	private String getURL(String restPath) {
		return String.format(API_URL + "/" + restPath + "?" + API_KEY_TOKEN_PARAM);
	}

	public String getBoardId() {
		if (boardId == null) {
			String response = trelloGetRequest("member/me/boards");
			List<String> result = JsonPath.read(response, "$..*[?(@.name=='" + boardName + "')].id");
			if (result.size() == 1) {
				boardId = result.get(0);
			}
			LOG.debug("Board id for " + boardName + " is " + boardId);
		}
		return boardId;
	}

	private String trelloGetRequest(String path) {
		String url = getURL(path);
		String response = restTemplate.getForObject(url, String.class, applicationKey, applicationToken);
		LOG.debug("Response from " + path + ": " + response);
		return response;
	}

	public String getBoardName() {
		return boardName;
	}

}
