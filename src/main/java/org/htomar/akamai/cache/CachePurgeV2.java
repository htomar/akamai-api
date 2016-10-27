package org.htomar.akamai.cache;

import org.htomar.akamai.cache.auth.BasicAuth;
import org.htomar.akamai.headers.CustomHeaders;
import org.htomar.openakamai.edge.auth.request.PurgeRequest;
import org.htomar.openakamai.edge.auth.request.PurgeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CachePurgeV2 {
	/**
	 * The logger used for logging.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CachePurgeV2.class);
	public static final String DEFAULT_INVALIDATE_ENDPOINT = "https://api.ccu.akamai.com/ccu/v2/queues/default";

	public PurgeResponse purgeByURL(final PurgeRequest purgeRequest,
			final BasicAuth basicAuth) throws RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		LOGGER.debug(purgeRequest.toString());
		ResponseEntity<PurgeResponse> responseEntity = restTemplate.exchange(
				DEFAULT_INVALIDATE_ENDPOINT, HttpMethod.POST,
				new HttpEntity<>(purgeRequest, new CustomHeaders(basicAuth)),
				PurgeResponse.class);
		LOGGER.info(responseEntity.getBody().toString());
		return responseEntity.getBody();
	}

	public PurgeResponse purgeByCPCode(final PurgeRequest purgeRequest,
			final BasicAuth basicAuth) throws RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		LOGGER.debug(purgeRequest.toString());
		purgeRequest.setType("cpcode");
		ResponseEntity<PurgeResponse> responseEntity = restTemplate.exchange(
				DEFAULT_INVALIDATE_ENDPOINT, HttpMethod.POST,
				new HttpEntity<>(purgeRequest, new CustomHeaders(basicAuth)),
				PurgeResponse.class);
		LOGGER.info(responseEntity.getBody().toString());
		return responseEntity.getBody();
	}
}
