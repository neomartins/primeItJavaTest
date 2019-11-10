package com.connectgroup;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.connectgroup.fileReader.FileReader;
import com.connectgroup.model.RequestLog;

public class DataFilterer {

	public static Collection<?> filterByCountry(Reader source, String country) {
		FileReader fileReader = new FileReader();
		return fileReader.getFile(source).stream().filter(request -> request.getCountry().equals(country))
				.collect(Collectors.toList());
	}

	public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
		FileReader fileReader = new FileReader();
		return fileReader.getFile(source).stream()
				.filter(request -> request.getCountry().equals(country) && request.getRequestDuration() > limit)
				.collect(Collectors.toList());
	}

	public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
		FileReader fileReader = new FileReader();
		List<RequestLog> requestLogs = fileReader.getFile(source);
		BigDecimal averageResponseTime = new BigDecimal(
				requestLogs.stream().mapToInt((x) -> x.getRequestDuration()).summaryStatistics().getAverage());
		return requestLogs.stream()
				.filter(request -> new BigDecimal(request.getRequestDuration()).compareTo(averageResponseTime) > 0)
				.collect(Collectors.toList());
	}

}