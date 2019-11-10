package com.connectgroup.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.connectgroup.model.RequestLog;

public class FileReader {

	public List<RequestLog> getFile(Reader source) {
		List<RequestLog> inputList = new ArrayList<RequestLog>();
		try {
			BufferedReader breader = new BufferedReader(source);
			inputList = breader.lines().skip(1).map(mountRequestLog).filter(request -> request != null)
					.collect(Collectors.toList());
			breader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputList;
	}

	private static Function<String, RequestLog> mountRequestLog = (lines) -> {
		RequestLog item = new RequestLog();

		List<String> listLine = Arrays.stream(lines.split(",")).filter(line -> line != null)
				.collect(Collectors.toList());

		Timestamp stamp = new Timestamp(Long.parseLong(listLine.get(0)));
		item.setRequestMade(new Date(stamp.getTime()));
		item.setCountry(listLine.get(1));
		item.setRequestDuration(Integer.valueOf(listLine.get(2)));
		return item;
    };
}
