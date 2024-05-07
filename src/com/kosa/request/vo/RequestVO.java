package com.kosa.request.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RequestVO {
	private int requestId;
	private String requestMovieTitle;
	private int requestCount;
	private String movieNo;
}
